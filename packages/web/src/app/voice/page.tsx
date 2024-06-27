'use client'

import { useEffect, useRef, useState } from 'react'
import { Client } from '@stomp/stompjs'
import SockJS from 'sockjs-client'
import { v4 as uuidv4 } from 'uuid'

export default function Home() {
  const localVideoRef = useRef<HTMLVideoElement>(null)
  const remoteVideoContainerRef = useRef<HTMLDivElement>(null)
  const remoteVideoRefs = useRef<Map<string, HTMLVideoElement>>(new Map())
  const [localStream, setLocalStream] = useState<MediaStream | null>(null)
  const [peerInfo, setPeerInfo] = useState<Map<string, RTCPeerConnection>>(new Map())
  const [roomId, setRoomId] = useState<string>('')
  const [otherKeyList, setOtherKeyList] = useState<string[]>([])
  const [myKey] = useState<string>(uuidv4())
  const stompClient = useRef<Client | null>(null)
  const config: RTCConfiguration = {
    iceServers: [{ urls: 'stun:stun.l.google.com:19302' }],
  }

  useEffect(() => {
    const getMedia = async () => {
      try {
        const stream = await navigator.mediaDevices.getUserMedia({
          video: true,
          audio: true,
        })
        if (localVideoRef.current) {
          localVideoRef.current.srcObject = stream
        }
        setLocalStream(stream)
        console.log('Local stream set', stream)
      } catch (error) {
        console.error('Error accessing media devices.', error)
      }
    }

    getMedia()
  }, [])

  const onIceCandidate = (iceEvent: RTCPeerConnectionIceEvent, otherKey: string) => {
    if (iceEvent.candidate) {
      console.log(`Sending ICE candidate to ${otherKey}`, iceEvent.candidate)
      stompClient.current?.publish({
        destination: `/pub/peer/iceCandidate/${otherKey}/${roomId}`,
        body: JSON.stringify({
          key: myKey,
          body: iceEvent.candidate,
        }),
      })
    }
  }

  const onTrack = (trackEvent: RTCTrackEvent, otherKey: string) => {
    console.log(`Received remote track from ${otherKey}`, trackEvent.streams)
    const [stream] = trackEvent.streams
    let video = remoteVideoRefs.current.get(otherKey)

    if (!video) {
      video = document.createElement('video')
      video.autoplay = true
      video.controls = true
      video.id = otherKey
      video.srcObject = stream

      const track = document.createElement('track')
      track.kind = 'captions'
      track.srclang = 'en'
      track.label = 'English'
      track.default = true
      video.appendChild(track)

      if (remoteVideoContainerRef.current) {
        remoteVideoContainerRef.current.appendChild(video)
      }

      remoteVideoRefs.current.set(otherKey, video)
      console.log(`Added remote video for ${otherKey}`)
    }
  }

  const createPeerConnection = async (otherKey: string): Promise<RTCPeerConnection> => {
    console.log(`Creating peer connection for ${otherKey}`)
    const pc = new RTCPeerConnection(config)
    pc.addEventListener('icecandidate', (event) => {
      onIceCandidate(event, otherKey)
    })
    pc.addEventListener('track', (event) => {
      onTrack(event, otherKey)
    })
    if (localStream) {
      localStream.getTracks().forEach((track) => {
        pc.addTrack(track, localStream)
      })
      console.log('Local stream tracks added to peer connection')
    }
    return pc
  }

  const setLocalAndSendMessage = (pc: RTCPeerConnection, sessionDescription: RTCSessionDescriptionInit) => {
    pc.setLocalDescription(sessionDescription)
    console.log('Local description set and sent', sessionDescription)
  }

  const sendAnswer = (pc: RTCPeerConnection, otherKey: string) => {
    pc.createAnswer().then((answer) => {
      setLocalAndSendMessage(pc, answer)
      console.log(`Sending answer to ${otherKey}`, answer)
      stompClient.current?.publish({
        destination: `/pub/peer/answer/${otherKey}/${roomId}`,
        body: JSON.stringify({
          key: myKey,
          body: answer,
        }),
      })
    })
  }

  const connectSocket = async (): Promise<void> => {
    const socket = new SockJS('https://spacestars.kr/api/v1/wsvoice')
    stompClient.current = new Client({
      webSocketFactory: () => socket,
      debug: (str) => {
        console.log(str)
      },
      onConnect: (frame) => {
        console.log(`Connected to server ${frame.headers.server}`)
        console.log('Connected to WebRTC server')

        stompClient.current?.subscribe(
          `/sub/peer/iceCandidate/${myKey}/${roomId}`,
          (message) => {
            console.log('Received ICE candidate message')
            const { key, body: candidate } = JSON.parse(message.body)
            const pc = peerInfo.get(key)
            if (pc) {
              console.log(`Adding ICE candidate for ${key}`, candidate)
              pc.addIceCandidate(
                new RTCIceCandidate({
                  candidate: candidate.candidate,
                  sdpMLineIndex: candidate.sdpMLineIndex,
                  sdpMid: candidate.sdpMid,
                }),
              )
            }
          },
        )

        stompClient.current?.subscribe(
          `/sub/peer/offer/${myKey}/${roomId}`,
          async (message) => {
            console.log('Received offer message')
            const { key, body: offer } = JSON.parse(message.body)
            const pc = await createPeerConnection(key)
            if (
              pc.signalingState === 'stable' ||
              pc.signalingState === 'have-local-offer'
            ) {
              await pc.setRemoteDescription(
                new RTCSessionDescription({
                  type: offer.type,
                  sdp: offer.sdp,
                }),
              )
              sendAnswer(pc, key)
              setPeerInfo(new Map(peerInfo.set(key, pc)))
            } else {
              console.warn(
                `Cannot set remote description in signaling state: ${pc.signalingState}`,
              )
            }
          },
        )

        stompClient.current?.subscribe(
          `/sub/peer/answer/${myKey}/${roomId}`,
          (message) => {
            console.log('Received answer message')
            const { key, body: answer } = JSON.parse(message.body)
            const pc = peerInfo.get(key)
            if (pc) {
              if (pc.signalingState === 'have-local-offer') {
                pc.setRemoteDescription(new RTCSessionDescription(answer))
                console.log(`Set remote description for ${key}`, answer)
              } else {
                console.warn(
                  `Cannot set remote description in signaling state: ${pc.signalingState}`,
                )
              }
            } else {
              console.warn(`PeerConnection for key ${key} not found`)
            }
          },
        )

        stompClient.current?.subscribe(`/sub/call/key`, () => {
          console.log('Received call key request')
          stompClient.current?.publish({
            destination: '/pub/send/key',
            body: JSON.stringify(myKey),
          })
        })

        stompClient.current?.subscribe(`/sub/send/key`, (message) => {
          console.log('Received send key message')
          const key = JSON.parse(message.body)
          if (myKey !== key && !otherKeyList.includes(key)) {
            setOtherKeyList((prev) => [...prev, key])
          }
        })

        // 모든 클라이언트에게 새로운 클라이언트의 참여를 알림
        stompClient.current?.publish({
          destination: `/pub/peer/join/${roomId}`,
          body: JSON.stringify({
            key: myKey,
          }),
        })
      },
    })

    stompClient.current.activate()
  }

  const sendOffer = (pc: RTCPeerConnection, otherKey: string) => {
    pc.createOffer().then((offer) => {
      setLocalAndSendMessage(pc, offer)
      console.log(`Sending offer to ${otherKey}`, offer)
      stompClient.current?.publish({
        destination: `/pub/peer/offer/${otherKey}/${roomId}`,
        body: JSON.stringify({
          key: myKey,
          body: offer,
        }),
      })
    })
  }

  const joinRoom = async () => {
    await connectSocket()
  }

  const startStreams = async () => {
    if (stompClient.current && stompClient.current.active) {
      console.log('Publishing call key request')
      stompClient.current.publish({
        destination: '/pub/call/key',
        body: JSON.stringify({}),
      })
      setTimeout(() => {
        otherKeyList.forEach(async (key) => {
          if (!peerInfo.has(key)) {
            const pc = await createPeerConnection(key)
            setPeerInfo((prev) => new Map(prev.set(key, pc)))
            sendOffer(pc, key)
          }
        })
      }, 1000)
    } else {
      console.error('STOMP client is not connected')
    }
  }

  return (
    <div>
      <div>
        <input
          type="number"
          onChange={(e) => setRoomId(e.target.value)}
          placeholder="Enter Room ID"
        />
        <button type="button" onClick={joinRoom}>
          Enter Room
        </button>
        <button
          type="button"
          onClick={startStreams}
          style={{ display: localStream ? 'inline' : 'none' }}
        >
          Start Streams
        </button>
      </div>
      <video
        ref={localVideoRef}
        autoPlay
        playsInline
        controls
        style={{ display: localStream ? 'block' : 'none' }}
      >
        <track kind="captions" srcLang="en" label="English" default />
      </video>
      <div ref={remoteVideoContainerRef} />
    </div>
  )
}
