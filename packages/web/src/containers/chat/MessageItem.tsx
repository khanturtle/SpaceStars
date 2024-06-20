'use client'

import { usePathname } from 'next/navigation'

import Image from 'next/image'
import Link from 'next/link'

import { useEffect, useState } from 'react'

// import { RoomType } from '@/apis/chat'
import { ChatMessageType, RoomInfoType } from '@/types/ChatType'
import { useWebSocket } from '@/components/providers/socket-provider'
import { convertToKoreanTime } from '@/hooks/convertToLocaleTime'
import { defaultImage } from '@/store/defaultState'

export default function MessageItem({ room }: { room: any }) {
  console.log(room)
  // const [recentMsg, setRecentMsg] = useState<ChatMessageType>()
  // const [unReadCount, setUnReadCount] = useState<number>(
  //   roomInfo.recentMessage?.unReadCount || 0,
  // )

  // 현재 접속한 채팅방
  const pathName = usePathname().split('/')
  const currentRoomNumber = pathName[pathName.length - 1]

  // const stompClient = useWebSocket()

  // /** 한국 시간 변경 */
  // const createdAtLocale = convertToKoreanTime(
  //   roomInfo.recentMessage?.createdAt as string,
  // )

  // /** 현재 채팅방이면, 안 읽은 메시지 0 */
  // useEffect(() => {
  //   if (item.roomNumber === currentRoomNumber) {
  //     setUnReadCount(0)
  //   }
  // }, [])

  // /** 최근 메시지 소켓 연결 */
  // useEffect(() => {
  //   if (stompClient) {
  //     /** 채팅방 구독 */
  //     const subscription = stompClient.subscribe(
  //       `/sub/one-to-one/${item.roomNumber}`,
  //       (message) => {
  //         const payload = JSON.parse(message.body)
  //         setRecentMsg(payload)
  //         if (item.roomNumber !== currentRoomNumber) {
  //           setUnReadCount((prev) => prev + 1)
  //         }
  //       },
  //       {},
  //     )

  //     return () => {
  //       subscription.unsubscribe()
  //     }
  //   }
  //   return undefined
  // }, [stompClient])

  return (
    <Link
      href={`/dashboard/chat/${room.roomNumber}`}
      className={`flex items-center gap-4 px-2 py-2 transition-colors duration-300 rounded-md hover:bg-gray-100
        ${room.roomNumber === currentRoomNumber ? 'bg-gray-100 hover:bg-gray-200 border-gray-300' : 'hover:bg-gray-100 border-gray-200'}
        `}
    >
      <div className="relative w-12 h-12">
        <Image
          className="w-12 h-12 rounded-full"
          width={48}
          height={48}
          src={room.otherMemberInfo.profileImageUrl ?? defaultImage}
          alt={room.otherMemberInfo.nickname ?? 'anonymous'}
        />
      </div>

      {/* <div className="flex-1"> */}
      <div className={`mb-1 flex items-start justify-between`}>
        <h4 className="text-[#161616] text-base not-italic font-semibold leading-[normal]">
          {room.otherMemberInfo.nickname ?? '채팅방'}
        </h4>
        {/* <span className="text-[#869aa9] text-right text-[10px] not-italic font-normal leading-[normal]">
          {convertToKoreanTime(recentMsg?.createdAt as string) ??
            createdAtLocale ??
            ''}
        </span> */}
      </div>
      {/* 

        <div className="inline-flex items-center justify-between w-full">
          <p
            className={`text-ellipsis whitespace-nowrap overflow-hidden text-xs not-italic font-normal leading-[normal] max-w-[160px] ${unReadCount > 0 ? 'text-[#161616]' : 'text-[#869AA9]'}`}
          >
            {recentMsg?.content ??
              roomInfo.recentMessage?.lastChatMessage ??
              ''}
          </p>
          {unReadCount > 0 && (
            <div className="w-[23px] h-6 bg-[#f15c45] flex items-center justify-center rounded-lg">
              <p className="text-[color:var(--White-50,#fff)] text-xs not-italic font-semibold leading-[normal]">
                {unReadCount}
              </p>
            </div>
          )}
        </div>
      </div> */}
    </Link>
  )
}
