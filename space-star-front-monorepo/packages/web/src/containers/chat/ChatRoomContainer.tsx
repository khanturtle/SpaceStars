'use client'

import { useEffect, useState } from 'react'

import { EmojiIcon, FileIcon } from '@packages/ui'

import { getReadMessage, getUnreadMessage } from '@/apis/getPrevChatByClient'
import { useWebSocket } from '@/components/providers/socket-provider'
import { ChatMessageType } from '@/types/ChatType'

import ChatLogList from '../chat-room/ChatLogList'
import ChatInputBox from './ChatInputBox'
import ChatHeader from './ChatHeader'

import styles from './chat.module.css'

export default function ChatRoomContainer({
  roomNumber,
  UUID,
  token,
}: {
  roomNumber: string
  UUID: string
  token: string
}) {
  const [msgLog, setMsgLog] = useState<ChatMessageType[]>([])

  const stompClient = useWebSocket()

  // 입 퇴장 메시지
  const message = {
    senderUuid: UUID,
  }
  
  /** 이전 메시지 */
  useEffect(() => {
    const fetchData = async () => {
      // 읽은 메시지
      const readMsgData = await getReadMessage(roomNumber, token)
      const readMsg = readMsgData.result
      // 안읽은 메시지
      const unreadMsgData = await getUnreadMessage(roomNumber, token)
      const unreadMsg = unreadMsgData.result
      setMsgLog((prev) => [...prev, ...readMsg, ...unreadMsg])
    }

    fetchData()
  }, [])

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
      <ChatHeader roomNumber={roomNumber} token={token} UUID={UUID} />

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
