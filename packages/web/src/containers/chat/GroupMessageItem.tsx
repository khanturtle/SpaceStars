'use client'

import { usePathname } from 'next/navigation'

import Image from 'next/image'
import Link from 'next/link'

import { useEffect, useState } from 'react'

import {
  getRecentMessageByTeam,
  getUnreadMessageCountByTeam,
} from '@/apis/getChatByClient'
import { useWebSocket } from '@/components/providers/socket-provider'
import { convertToKoreanTime } from '@/hooks/convertToLocaleTime'
import { defaultImage } from '@/store/defaultState'
import { RecentMessageType } from '@/types/type'

export default function GroupMessageItem({
  room,
  token,
}: {
  room: any
  token: string
}) {
  const [recentMessage, setRecentMessage] = useState<RecentMessageType>()
  const [unReadCount, setUnReadCount] = useState<number>(0)

  // 현재 접속중인 채팅방
  const pathName = usePathname().split('/')
  const currentRoomNumber = pathName[pathName.length - 1]

  /** 최초로 최근 메시지, 안읽은 개수 불러오기 */
  useEffect(() => {
    const fetchRecentMessage = async () => {
      const result = await getRecentMessageByTeam(room.roomNumber, token)
      if (result) {
        setRecentMessage(result)
      }

      const unRead = await getUnreadMessageCountByTeam(room.roomNumber, token)
      if (unRead) {
        setUnReadCount(unRead.unReadMessageCount)
      }
    }

    fetchRecentMessage()
  }, [])

  const stompClient = useWebSocket()

  /** 현재 채팅방이면, 안 읽은 메시지 초기화 */
  useEffect(() => {
    if (room.roomNumber === currentRoomNumber) {
      setUnReadCount(0)
    }
  }, [currentRoomNumber])

  /** 최근 메시지 소켓 연결 */
  useEffect(() => {
    if (stompClient) {
      /** 채팅방 구독 */
      const subscription = stompClient.subscribe(
        `/sub/team/${room.roomNumber}`,
        (message) => {
          const payload = JSON.parse(message.body)
          setRecentMessage({
            senderUuid: payload.senderUuid,
            lastChatMessage: payload.content,
            createdAt: payload.createdAt,
          })

          if (room.roomNumber !== currentRoomNumber) {
            setUnReadCount((prev) => prev + 1)
          }
        },
        {},
      )

      return () => {
        subscription.unsubscribe()
      }
    }
    return undefined
  }, [stompClient])

  return (
    <Link
      href={`/dashboard/chat/group/${room.roomNumber}`}
      className={`flex items-center gap-4 px-2 py-2 transition-colors duration-300 rounded-md hover:bg-gray-100
        ${room.roomNumber === currentRoomNumber ? 'bg-gray-100 hover:bg-gray-200 border-gray-300' : 'hover:bg-gray-100 border-gray-200'}
        `}
    >
      <div className="relative w-12 h-12">
        <Image
          className="w-12 h-12 rounded-full"
          width={48}
          height={48}
          // FIXME: 게임 정보도 받아오기
          src={room.gameInfo ?? defaultImage}
          alt={room.gameInfo ?? 'anonymous'}
        />
      </div>

      <div className="flex-1">
        <div className={`mb-1 flex items-start justify-between`}>
          <h4 className="text-[#161616] text-base not-italic font-semibold leading-[normal]">
            {room.roomName ?? '채팅방'}
          </h4>
          <span className="text-[#869aa9] text-right text-[10px] not-italic font-normal leading-[normal]">
            {recentMessage?.createdAt
              ? convertToKoreanTime(recentMessage.createdAt)
              : ''}
          </span>
        </div>
        <div className="inline-flex items-center justify-between w-full">
          <p
            className={`text-ellipsis whitespace-nowrap overflow-hidden text-xs not-italic font-normal leading-[normal] max-w-[160px] ${unReadCount > 0 ? 'text-[#161616]' : 'text-[#869AA9]'}`}
          >
            {recentMessage?.lastChatMessage ?? ''}
          </p>
          {unReadCount > 0 && (
            <div className="w-[23px] h-6 bg-[#f15c45] flex items-center justify-center rounded-lg">
              <p className="text-[color:var(--White-50,#fff)] text-xs not-italic font-semibold leading-[normal]">
                {unReadCount}
              </p>
            </div>
          )}
        </div>
      </div>
    </Link>
  )
}
