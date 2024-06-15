'use client'

import { useWebSocket } from '@/components/providers/socket-provider'
import { EmojiIcon, FileIcon } from '@packages/ui'
import { Frame } from '@stomp/stompjs'
import { useEffect, useState } from 'react'
import styles from './chat.module.css'
import ChatInputBox from './ChatInputBox'

export default function ChatRoomContainer() {
  const [chatLog, setChatLog] = useState([])

  const stompClient = useWebSocket()

  /** 채팅 소켓 연결 */
  // FIXME: url 수정
  useEffect(() => {
    if (stompClient) {
      const subscription = stompClient.subscribe(
        '채팅 소켓 구독',
        function handleMessageFunction(frame: Frame) {
          const receivedChat = JSON.parse(frame.body)
          // setChatLog((prev) => [...prev, receivedChat])
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
    <div className={styles.chatroom}>
      <div className={styles.header}>ㅇㅅㅇ</div>
      <div className={styles.msg}>{chatLog}</div>

      <ChatInputBox>
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
