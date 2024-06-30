'use client'

import Image from 'next/image'
import { useRouter } from 'next/navigation'

import { useContext, useEffect, useState, useReducer } from 'react'

import { Ellipsis, Phone } from 'lucide-react'

import { ArrowIcon, Button } from '@packages/ui'

import { getGameById } from '@/apis/getGame'
import {
  getGroupChatInfo,
  getTeamChatRoomsMember,
} from '@/apis/getGroupChatByClient'
import { ModalContext } from '@/components/providers/modal-provider'
import { GameType, GroupChatInfo } from '@/types/type'

import styles from './chat.module.css'
import FormLayout from '@/components/form/formLayout'
import { exitChatRoom } from '@/apis/actionGroupChat'

export default function GroupChatHeader({
  roomNumber,
  token,
}: {
  roomNumber: string
  token: string
}) {
  const [roomInfo, setRoomInfo] = useState<GroupChatInfo>()
  const [gameInfo, setGameInfo] = useState<GameType>()
  const [memberInfo, setMemberInfo] = useState()

  const { openModal, closeModal } = useContext(ModalContext)

  const router = useRouter()

  const [isDropdownOpen, setIsDropdownOpen] = useReducer(
    (state) => !state,
    false,
  )

  useEffect(() => {
    const fetchData = async () => {
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
  }, [roomNumber])

  // 외부 클릭 시 닫기
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

  // TODO: 보이스 연결
  const handleCall = () => {
    // 누르면, zustand state 변화 -> ChatRightSide 에 image 테두리 보라색
  }

  const exitRoom = async () => {
    await exitChatRoom(roomNumber)
    closeModal()
    router.replace('/dashboard/chat')
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
          <Phone stroke="#869AA9" />
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
      {/* {isAdmin && (
          <button
            onClick={onEdit}
            className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 mb-4"
          >
            정보 수정
          </button>
        )} */}
      <h3 className="text-lg font-semibold mb-2">참가자 목록</h3>
      <ul>
        {/* {users.map((user) => (
            <li
              key={user.id}
              className="flex justify-between items-center mb-2"
            >
              <span>{user.name}</span>
              {isAdmin && (
                <button
                  onClick={() => onKickUser(user.id)}
                  className="bg-red-500 text-white px-2 py-1 rounded text-sm hover:bg-red-600"
                >
                  강퇴
                </button>
              )}
            </li>
          ))} */}
      </ul>
      <Button label="닫기" onClick={closeModal} primary />
    </div>
  )
}
