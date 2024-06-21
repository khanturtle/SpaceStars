'use client'

import { useEffect, useState } from 'react'

import { EmojiIcon, FileIcon } from '@packages/ui'

import { useWebSocket } from '@/components/providers/socket-provider'
import { ChatMessageType } from '@/types/ChatType'

import styles from './chat.module.css'
import ChatInputBox from './ChatInputBox'

import ChatLogList from '../chat-room/ChatLogList'

type roomMemberDataType = {
  profileImageUrl: string
  nickname: string
  index: number
  memberUuid: string
}
export default function ChatRoomContainer({
  roomNumber,
  roomMemberData,
  UUID,
}: {
  roomNumber: string
  roomMemberData: roomMemberDataType[]
  UUID: string
}) {
  const [msgLog, setMsgLog] = useState<ChatMessageType[]>([])

  const stompClient = useWebSocket()

  // 입 퇴장 메시지
  const message = {
    senderUuid: UUID,
  }

  // TODO: 이전 메시지 받아와서 위로 보여주기

  /** 채팅 소켓 연결 */
  useEffect(() => {
    if (stompClient) {
      /** 채팅방 구독 */
      const subscription = stompClient.subscribe(
        `/sub/one-to-one/${roomNumber}`,
        (message) => {
          const payload = JSON.parse(message.body)
          setMsgLog((prev) => [...prev, payload])
        },
        {},
      )

      /** 채팅방 입장 */
      stompClient.send(
        `/pub/one-to-one/join/${roomNumber}`,
        {},
        JSON.stringify(message),
      )

      /** 채팅방 퇴장 */
      return () => {
        subscription.unsubscribe()
        stompClient.send(
          `/pub/one-to-one/exit/${roomNumber}`,
          {},
          JSON.stringify(message),
        )
      }
    }
    return undefined
  }, [stompClient])

  return (
    <div className={styles.chatroom}>
      {/* <div className={styles.header}>ㅇㅅㅇ</div> */}

      <ChatLogList msgLog={msgLog} UUID={UUID} />

      <ChatInputBox roomNumber={roomNumber} UUID={UUID}>
        <ChatInputBox.IconBtn
          icon={<FileIcon />}
          handleClick={() => console.log('file')}
        />
        <ChatInputBox.IconBtn
          icon={<EmojiIcon />}
          handleClick={() => console.log('emoji')}
        />
      </ChatInputBox>
    </div>
  )
}
