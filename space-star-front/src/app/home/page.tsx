'use client'

// import { useEffect, useRef, useState } from 'react'

// import { Client } from '@stomp/stompjs'
// import SockJS from 'sockjs-client'
// import { v4 as uuidv4 } from 'uuid'

export default function Home() {
  // const localVideoRef = useRef(null)
  // const remoteVideoRef = useRef(null)
  // const [localStream, setLocalStream] = useState(null)
  // const [peerInfo, setPeerInfo] = useState(new Map())
  // const [roomId, setRoomId] = useState('')
  // const [otherKeyList, setOtherKeyList] = useState([])
  // const [myKey] = useState(uuidv4())
  // const stompClient = useRef(null)
  // const config = {
  //   iceServers: [{ urls: 'stun:stun.l.google.com:19302' }],
  // }

  // useEffect(() => {
  // const getMedia = async () => {
  //   try {
  //     const stream = await navigator.mediaDevices.getUserMedia({
  //       video: true,
  //       audio: true,
  //     })
  //     localVideoRef.current.srcObject = stream
  //     setLocalStream(stream)
  //     console.log('Local stream set', stream)
  //   } catch (error) {
  //     console.error('Error accessing media devices.', error)
  //   }
  // }
  // getMedia()
  // }, [])

  // const onIceCandidate = (iceEvent, otherKey) => {
  //   if (iceEvent.candidate) {
  //     console.log(`Sending ICE candidate to ${otherKey}`, iceEvent.candidate)
  //     stompClient.current.publish({
  //       destination: `/app/peer/iceCandidate/${otherKey}/${roomId}`,
  //       body: JSON.stringify({
  //         key: myKey,
  //         body: iceEvent.candidate,
  //       }),
  //     })
  //   }
  // }

  // const onTrack = (trackEvent, otherKey) => {
  //   console.log(`Received remote track from ${otherKey}`, trackEvent.streams)
  //   const [stream] = trackEvent.streams
  //   if (!document.getElementById(otherKey)) {
  //     const video = document.createElement('video')
  //     video.autoplay = true
  //     video.controls = true
  //     video.id = otherKey
  //     video.srcObject = stream

  //     const track = document.createElement('track')
  //     track.kind = 'captions'
  //     track.srclang = 'en'
  //     track.label = 'English'
  //     track.default = true
  //     video.appendChild(track)

  //     remoteVideoRef.current.appendChild(video)
  //     console.log(`Added remote video for ${otherKey}`)
  //   }
  // }

  // const createPeerConnection = async (otherKey) => {
  //   console.log(`Creating peer connection for ${otherKey}`)
  //   const pc = new RTCPeerConnection(config)
  //   pc.addEventListener('icecandidate', (event) => {
  //     onIceCandidate(event, otherKey)
  //   })
  //   pc.addEventListener('track', (event) => {
  //     onTrack(event, otherKey)
  //   })
  //   if (localStream) {
  //     localStream.getTracks().forEach((track) => {
  //       pc.addTrack(track, localStream)
  //     })
  //     console.log('Local stream tracks added to peer connection')
  //   }
  //   return pc
  // }

  // const setLocalAndSendMessage = (pc, sessionDescription) => {
  //   pc.setLocalDescription(sessionDescription)
  //   console.log('Local description set and sent', sessionDescription)
  // }

  // const sendAnswer = (pc, otherKey) => {
  // pc.createAnswer().then((answer) => {
  //   setLocalAndSendMessage(pc, answer)
  //   console.log(`Sending answer to ${otherKey}`, answer)
  //   stompClient.current.publish({
  //     destination: `/app/peer/answer/${otherKey}/${roomId}`,
  //     body: JSON.stringify({
  //       key: myKey,
  //       body: answer,
  //     }),
  //   })
  // })
  // }

  // const connectSocket = async () => {
  //   const socket = new SockJS('/signaling')
  //   stompClient.current = new Client({
  //     webSocketFactory: () => socket,
  //     debug: (str) => {
  //       console.log(str)
  //     },
  //     onConnect: (frame) => {
  //       console.log(`Connected to server ${frame.headers.server}`)
  //       console.log('Connected to WebRTC server')

  //       stompClient.current.subscribe(
  //         `/topic/peer/iceCandidate/${myKey}/${roomId}`,
  //         (message) => {
  //           console.log('Received ICE candidate message')
  //           const { key, body: candidate } = JSON.parse(message.body)
  //           const pc = peerInfo.get(key)
  //           if (pc) {
  //             console.log(`Adding ICE candidate for ${key}`, candidate)
  //             pc.addIceCandidate(
  //               new RTCIceCandidate({
  //                 candidate: candidate.candidate,
  //                 sdpMLineIndex: candidate.sdpMLineIndex,
  //                 sdpMid: candidate.sdpMid,
  //               }),
  //             )
  //           }
  //         },
  //       )

  //       stompClient.current.subscribe(
  //         `/topic/peer/offer/${myKey}/${roomId}`,
  //         async (message) => {
  //           console.log('Received offer message')
  //           const { key, body: offer } = JSON.parse(message.body)
  //           const pc = await createPeerConnection(key)
  //           if (
  //             pc.signalingState === 'stable' ||
  //             pc.signalingState === 'have-local-offer'
  //           ) {
  //             await pc.setRemoteDescription(
  //               new RTCSessionDescription({
  //                 type: offer.type,
  //                 sdp: offer.sdp,
  //               }),
  //             )
  //             sendAnswer(pc, key)
  //             setPeerInfo(new Map(peerInfo.set(key, pc)))
  //           } else {
  //             console.warn(
  //               `Cannot set remote description in signaling state: ${pc.signalingState}`,
  //             )
  //           }
  //         },
  //       )

  //       stompClient.current.subscribe(
  //         `/topic/peer/answer/${myKey}/${roomId}`,
  //         (message) => {
  //           console.log('Received answer message')
  //           const { key, body: answer } = JSON.parse(message.body)
  //           const pc = peerInfo.get(key)
  //           if (pc && pc.signalingState === 'have-local-offer') {
  //             pc.setRemoteDescription(new RTCSessionDescription(answer))
  //             console.log(`Set remote description for ${key}`, answer)
  //           } else {
  //             console.warn(
  //               `Cannot set remote description in signaling state: ${pc.signalingState}`,
  //             )
  //           }
  //         },
  //       )

  //       stompClient.current.subscribe(`/topic/call/key`, () => {
  //         console.log('Received call key request')
  //         stompClient.current.publish({
  //           destination: '/app/send/key',
  //           body: JSON.stringify(myKey),
  //         })
  //       })

  //       stompClient.current.subscribe(`/topic/send/key`, (message) => {
  //         console.log('Received send key message')
  //         const key = JSON.parse(message.body)
  //         if (myKey !== key && !otherKeyList.includes(key)) {
  //           setOtherKeyList([...otherKeyList, key])
  //         }
  //       })
  //     },
  //   })

  //   stompClient.current.activate()
  // }

  // const sendOffer = (pc, otherKey) => {
  //   pc.createOffer().then((offer) => {
  //     setLocalAndSendMessage(pc, offer)
  //     console.log(`Sending offer to ${otherKey}`, offer)
  //     stompClient.current.publish({
  //       destination: `/app/peer/offer/${otherKey}/${roomId}`,
  //       body: JSON.stringify({
  //         key: myKey,
  //         body: offer,
  //       }),
  //     })
  //   })
  // }

  // const joinRoom = async () => {
  //   await connectSocket()
  // }

  // const startStreams = async () => {
  //   if (stompClient.current && stompClient.current.active) {
  //     console.log('Publishing call key request')
  //     stompClient.current.publish({
  //       destination: '/app/call/key',
  //       body: JSON.stringify({}),
  //     })
  //     setTimeout(() => {
  //       otherKeyList.forEach(async (key) => {
  //         if (!peerInfo.has(key)) {
  //           const pc = await createPeerConnection(key)
  //           setPeerInfo(new Map(peerInfo.set(key, pc)))
  //           sendOffer(pc, key)
  //         }
  //       })
  //     }, 1000)
  //   } else {
  //     console.error('STOMP client is not connected')
  //   }
  // }

  return (
    <div>
      {/* <div>
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
      <div ref={remoteVideoRef} /> */}
    </div>
  )
}
