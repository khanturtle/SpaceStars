'use client'

import Image from 'next/image'
import { useRouter } from 'next/navigation'

import { useContext, useEffect, useRef, useState, useReducer } from 'react'

import { Ellipsis, Phone } from 'lucide-react'
import { Client } from '@stomp/stompjs'
import SockJS from 'sockjs-client'

import { ArrowIcon, Button } from '@packages/ui'

import { exitChatRoom } from '@/apis/actionGroupChat'
import { getGameById } from '@/apis/getGame'
import {
  getGroupChatInfo,
  getTeamChatRoomsMember,
} from '@/apis/getGroupChatByClient'
import { ModalContext } from '@/components/providers/modal-provider'
import FormLayout from '@/components/form/formLayout'
import { GameType, GroupChatInfo } from '@/types/type'
import { useVoiceStore } from '@/store/voiceRoomStore'

import styles from './chat.module.css'

export default function GroupChatHeader({
  roomNumber,
  token,
  UUID,
}: {
  roomNumber: string
  token: string
  UUID: string
}) {
  const [roomInfo, setRoomInfo] = useState<GroupChatInfo>()
  const [gameInfo, setGameInfo] = useState<GameType>()
  const [memberInfo, setMemberInfo] = useState()
  const [isConnected, setIsConnected] = useState(false) // 보이스 연결 상태 관리

  const { openModal, closeModal } = useContext(ModalContext)

  const router = useRouter()

  const { isVoice, setIsVoice } = useVoiceStore()

  const [isDropdownOpen, setIsDropdownOpen] = useReducer(
    (state) => !state,
    false,
  )

  const remoteAudioContainerRef = useRef<HTMLDivElement>(null)
  const remoteAudioRefs = useRef<Map<string, HTMLAudioElement>>(new Map())
  const [localStream, setLocalStream] = useState<MediaStream | null>(null)
  const [peerInfo, setPeerInfo] = useState<Map<string, RTCPeerConnection>>(new Map())
  const [otherKeyList, setOtherKeyList] = useState<string[]>([])
  const [myKey, setMyKey] = useState<string>(UUID)
  const stompClient = useRef<Client | null>(null)
  const config: RTCConfiguration = {
    iceServers: [{ urls: 'stun:stun.l.google.com:19302' }],
  }

  useEffect(() => {
    if (!UUID) {
      console.error('UUID is undefined')
    }
  }, [UUID])

  useEffect(() => {
    const fetchData = async () => {
      setMyKey(UUID)
      const roomData = await getGroupChatInfo(roomNumber, token)
      const gameData = await getGameById(roomData.gameId)
      const memberData = await getTeamChatRoomsMember(roomNumber, token)

      setRoomInfo(roomData)
      if (gameData) {
        setGameInfo(gameData)
      }
      setMemberInfo(memberData)
    }

    fetchData()
  }, [roomNumber, token])

  useEffect(() => {
    const getMedia = async () => {
      try {
        const stream = await navigator.mediaDevices.getUserMedia({
          audio: true,
        })
        setLocalStream(stream)
        console.log('Local stream set', stream)
      } catch (error) {
        console.error('Error accessing media devices.', error)
      }
    }

    getMedia()
  }, [])

  useEffect(() => {
    const closeDropdown = (e: MouseEvent) => {
      if (
        isDropdownOpen &&
        !(e.target as Element).closest('.dropdown-container')
      ) {
        setIsDropdownOpen()
      }
    }

    document.addEventListener('click', closeDropdown)

    return () => {
      document.removeEventListener('click', closeDropdown)
    }
  }, [isDropdownOpen])

  const onIceCandidate = (iceEvent: RTCPeerConnectionIceEvent, otherKey: string) => {
    if (iceEvent.candidate) {
      console.log(`Sending ICE candidate to ${otherKey}`, iceEvent.candidate)
      if (stompClient.current && stompClient.current.connected) {
        stompClient.current.publish({
          destination: `/pub/peer/iceCandidate/${otherKey}/${roomNumber}`,
          body: JSON.stringify({
            key: myKey,
            body: iceEvent.candidate,
          }),
        })
      }
    }
  }

  const onTrack = (trackEvent: RTCTrackEvent, otherKey: string) => {
    console.log(`Received remote track from ${otherKey}`, trackEvent.streams)
    const [stream] = trackEvent.streams
    let audio = remoteAudioRefs.current.get(otherKey)

    if (!audio) {
      audio = document.createElement('audio')
      audio.autoplay = true
      audio.controls = true
      audio.id = otherKey
      audio.srcObject = stream

      const track = document.createElement('track')
      track.kind = 'captions'
      track.srclang = 'en'
      track.label = 'English'
      track.default = true
      audio.appendChild(track)

      if (remoteAudioContainerRef.current) {
        remoteAudioContainerRef.current.appendChild(audio)
      }

      remoteAudioRefs.current.set(otherKey, audio)
      console.log(`Added remote audio for ${otherKey}`)
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
      if (stompClient.current && stompClient.current.connected) {
        stompClient.current.publish({
          destination: `/pub/peer/answer/${otherKey}/${roomNumber}`,
          body: JSON.stringify({
            key: myKey,
            body: answer,
          }),
        })
      }
    })
  }

  const connectSocket = async (): Promise<void> => {
    const socket = new SockJS('https://spacestars.kr/api/v1/wsvoice')
    stompClient.current = new Client({
      webSocketFactory: () => socket,
      debug: (str) => {
        console.log(str)
      },
      onConnect: async (frame) => {
        console.log(`Connected to server ${frame.headers.server}`)
        console.log('Connected to WebRTC server')

        stompClient.current?.subscribe(
          `/sub/peer/iceCandidate/${myKey}/${roomNumber}`,
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
          `/sub/peer/offer/${myKey}/${roomNumber}`,
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
          `/sub/peer/answer/${myKey}/${roomNumber}`,
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

        stompClient.current?.subscribe(`/sub/call/key/${roomNumber}`, () => {
          console.log('Received call key request')
          if (stompClient.current && stompClient.current.connected) {
            stompClient.current.publish({
              destination: `/pub/send/key/${roomNumber}`,
              body: JSON.stringify({ roomNumber, key: myKey }),
            })
          }
        })

        stompClient.current?.subscribe(`/sub/send/key/${roomNumber}`, async (message) => {
          console.log('Received send key message')
          const { key } = JSON.parse(message.body)
          if (myKey !== key && !otherKeyList.includes(key)) {
            setOtherKeyList((prev) => [...prev, key])
            const pc = await createPeerConnection(key)
            sendOffer(pc, key)
          }
        })

        // 모든 클라이언트에게 새로운 클라이언트의 참여를 알림
        if (stompClient.current && stompClient.current.connected) {
          stompClient.current.publish({
            destination: `/pub/peer/join/${roomNumber}`,
            body: JSON.stringify({
              key: myKey,
            }),
          })
        }

        // WebSocket 연결이 완료되었음을 설정하고, 그때 startStreams 호출
        setIsConnected(true)
        startStreams()
      },
    })

    stompClient.current.activate()
  }

  const sendOffer = (pc: RTCPeerConnection, otherKey: string) => {
    pc.createOffer().then((offer) => {
      setLocalAndSendMessage(pc, offer)
      console.log(`Sending offer to ${otherKey}`, offer)
      if (stompClient.current && stompClient.current.connected) {
        stompClient.current.publish({
          destination: `/pub/peer/offer/${otherKey}/${roomNumber}`,
          body: JSON.stringify({
            key: myKey,
            body: offer,
          }),
        })
      }
    })
  }

  const startStreams = async () => {
    console.log('Starting streams')
    if (stompClient.current && stompClient.current.connected) {
      console.log('Publishing call key request')
      stompClient.current.publish({
        destination: `/pub/call/key/${roomNumber}`,
        body: JSON.stringify({ roomNumber, key: myKey }),  // 방 번호와 키를 메시지 본문에 포함
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

  const disconnect = async () => {
    peerInfo.forEach((pc) => {
      pc.close()
    })
    setPeerInfo(new Map())

    // STOMP 클라이언트를 비활성화합니다.
    if (stompClient.current) {
      stompClient.current.deactivate()
    }

    // 비디오 스트림을 중지합니다.
    if (localStream) {
      localStream.getTracks().forEach((track) => track.stop())
      setLocalStream(null)
    }

    // 원격 오디오 요소를 제거합니다.
    remoteAudioRefs.current.forEach((audio) => {
      if (remoteAudioContainerRef.current) {
        remoteAudioContainerRef.current.removeChild(audio)
      }
    })
    remoteAudioRefs.current.clear()

    console.log('Disconnected from room')
    setIsConnected(false)
  }

  const handleCall = () => {
    if (isConnected) {
      disconnect()
    } else {
      connectSocket()
    }
  }

  const exitRoom = async () => {
    await exitChatRoom(roomNumber)
    closeModal()
    router.replace('/dashboard/chat')
  }

  const handleMore = () => {
    openModal(<div className={styles.roomPopup}></div>)
  }

  return (
    <div className={`${styles.header} chat-header`}>
      <button
        className={styles.back}
        onClick={() => {
          router.push('/dashboard/chat')
        }}
      >
        <ArrowIcon
          type="left"
          fill="var(--color-icon)"
          width="20"
          height="20"
        />
      </button>

      {gameInfo && (
        <Image
          src={gameInfo.gameImage}
          alt={gameInfo.gameName}
          width={48}
          height={48}
          className={styles.image}
        />
      )}
      <div className={styles.roomInfo}>
        <h3>{roomInfo?.roomName ?? ''}</h3>
        <p>{roomInfo?.memo ?? ''}</p>
      </div>

      <div className="flex-1" />

      <div className={`${styles.icons} relative`}>
        <button type="button" onClick={handleCall}>
          <Phone stroke={isConnected ? "red" : "green"} />
        </button>

        <button type="button" onClick={() => setIsDropdownOpen()}>
          <Ellipsis stroke="#869AA9" />
        </button>

        {isDropdownOpen && (
          <div className="dropdown-container absolute top-full right-0 bg-white rounded-md shadow-lg z-10 w-40">
            <div className="py-1">
              <button
                onClick={() => {
                  // openModal(
                  //   <InfoModal
                  //     closeModal={closeModal}
                  //     roomInfo={roomInfo}
                  //     gameInfo={gameInfo}
                  //     memberInfo={memberInfo}
                  //   />,
                  // )
                }}
                className="block w-full text-left px-6 py-4 text-sm text-gray-700 hover:bg-gray-100 truncate"
              >
                채팅방 정보
              </button>
              <button
                type="button"
                onClick={() => {
                  openModal(
                    <QuitModal closeModal={closeModal} exitRoom={exitRoom} />,
                  )
                }}
                className="block w-full text-left px-6 py-4 text-sm text-gray-700 hover:bg-gray-100 truncate"
              >
                채팅방 나가기
              </button>
            </div>
          </div>
        )}
      </div>
    </div>
  )
}

const QuitModal = ({
  closeModal,
  exitRoom,
}: {
  closeModal: () => void
  exitRoom: () => void
}) => {
  return (
    <div className="relative flex flex-col items-center h-full">
      <FormLayout
        className="relative h-full px-[80px] pt-[90px] pb-[80px]
                  flex flex-col items-center"
      >
        <FormLayout.Legend
          title="Exit Room"
          description="정말 채팅방에서 나가시겠습니까?"
        />
        <div className="flex gap-[15px] w-full">
          <Button label="취소" onClick={closeModal} />
          <Button
            label="나가기"
            onClick={exitRoom}
            primary
            backgroundColor="var(--danger)"
          />
        </div>
      </FormLayout>
    </div>
  )
}

const InfoModal = ({
  closeModal,
  roomInfo,
  gameInfo,
  memberInfo,
}: {
  closeModal: () => void
  roomInfo: any
  gameInfo: any
  memberInfo: any
}) => {
  console.log(roomInfo)
  console.log(gameInfo)
  console.log(memberInfo)
  return (
    <div className="relative flex flex-col items-center h-full px-[100px] pt-[50px] pb-[40px]">
      <h2 className="text-2xl font-bold mb-4">채팅방 정보</h2>
      <div className="mb-4">
        <h3 className="text-lg font-semibold">이름</h3>
        <p>{roomInfo.roomName}</p>
      </div>
      <div className="mb-4">
        <h3 className="text-lg font-semibold">설명</h3>
        <p>{roomInfo.memo}</p>
      </div>
      <h3 className="text-lg font-semibold mb-2">참가자 목록</h3>
      <ul>
      </ul>
      <Button label="닫기" onClick={closeModal} primary />
    </div>
  )
}
