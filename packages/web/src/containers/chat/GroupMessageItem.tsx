'use client'

import { usePathname } from 'next/navigation'

import Image from 'next/image'
import Link from 'next/link'

import { useEffect, useState } from 'react'

import {
  getRecentMessageByTeam,
  getUnreadMessageCountByTeam,
} from '@/apis/getChatByClient'
import { getGameById } from '@/apis/getGame'

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
  const stompClient = useWebSocket()

  const [recentMessage, setRecentMessage] = useState<RecentMessageType>()
  const [unReadCount, setUnReadCount] = useState<number>(0)
  const [gameImage, setGameImage] = useState<string>(defaultImage)

  // 현재 접속중인 채팅방
  const pathName = usePathname().split('/')
  const currentRoomNumber = pathName[pathName.length - 1]

  /** 최초로 최근 메시지, 안읽은 개수 불러오기
   * 게임 정보 불러오기 */
  useEffect(() => {
    const fetchRecentMessage = async () => {
      // 최근 메시지
      const result = await getRecentMessageByTeam(room.roomNumber, token)
      if (result) {
        setRecentMessage(result)
      }

      // 안 읽은 개수
      const unRead = await getUnreadMessageCountByTeam(room.roomNumber, token)
      if (unRead) {
        setUnReadCount(unRead.unReadMessageCount)
      }

      // 게임 정보
      const gameInfo = await getGameById(room.roomInfo.gameId)
      setGameImage(gameInfo?.gameImage ?? defaultImage)
    }

    fetchRecentMessage()
  }, [])

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
  }, [stompClient, currentRoomNumber])

  return (
    <Link
      href={`/dashboard/chat/group/${room.roomNumber}`}
      className={`flex items-center gap-4 px-2 py-2 transition-colors duration-300 rounded-md hover:bg-[color:var(--button-hover)]
        ${room.roomNumber === currentRoomNumber && 'bg-[color:var(--button-active)] hover:bg-[color:var(--button-hover)]'}`}
    >
      <div className="relative w-12 h-12">
        <Image
          className="w-12 h-12 rounded-full"
          fill
          sizes="(max-width: 768px) 100vw, (max-width: 1200px) 50vw, 33vw"
          src={gameImage}
          alt={gameImage}
          style={{
            objectFit: 'cover',
          }}
        />
      </div>

      <div className="flex-1">
        <div className={`mb-1 flex items-start justify-between`}>
          <h4 className="text-[color:var(--text)] text-base not-italic font-semibold leading-[normal]">
            {room.roomName ?? '채팅방'}
          </h4>
          <span className="text-[color:var(--grey-text)] text-right text-[10px] not-italic font-normal leading-[normal]">
            {recentMessage?.createdAt
              ? convertToKoreanTime(recentMessage.createdAt)
              : ''}
          </span>
        </div>
        <div className="inline-flex items-center justify-between w-full">
          <p
            className={`text-ellipsis whitespace-nowrap overflow-hidden text-xs not-italic font-normal leading-[normal] max-w-[160px] ${unReadCount > 0 ? 'text-[color:var(--text-title)]' : 'text-[color:var(--grey-text)]'}`}
          >
            {recentMessage?.lastChatMessage ?? ''}
          </p>
          {unReadCount > 0 && (
            <div className="w-[23px] h-6 bg-[color:var(----danger)] flex items-center justify-center rounded-lg">
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
