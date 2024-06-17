'use client'

import { useEffect, useState } from 'react'

import { EmojiIcon, FileIcon } from '@packages/ui'

import { useWebSocket } from '@/components/providers/socket-provider'

import styles from './chat.module.css'
import ChatInputBox from './ChatInputBox'

export default function ChatRoomContainer({
  roomNumber,
  UUID,
}: {
  roomNumber: string
  UUID: string
}) {
  const [chatLog, setChatLog] = useState([])

  const stompClient = useWebSocket()

  // 입 퇴장 메시지
  const message = {
    senderUuid: UUID,
  }

  /** 채팅 소켓 연결 */
  useEffect(() => {
    if (stompClient) {
      /** 채팅방 구독 */
      const subscription = stompClient.subscribe(
        `/sub/one-to-one/${roomNumber}`,
        (message) => {
          const payload = JSON.parse(message.body)
          console.log('p', payload)
          // setChatLog((prev) => [...prev, payload])
        },
        {},
      )

      /** 채팅방 입장 */
      stompClient.send(
        `/pub/one-to-one/join/${roomNumber}`,
        {},
        JSON.stringify(message),
      )

      return () => {
        subscription.unsubscribe()
        /** 채팅방 퇴장 */
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
      <div className={styles.msg}>
        {chatLog}
        {roomNumber}
      </div>

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
