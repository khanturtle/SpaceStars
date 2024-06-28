'use client'

import { useEffect, useState } from 'react'

import { EmojiIcon, FileIcon } from '@packages/ui'

import {
  getReadMessageByTeam,
  getUnreadMessageByTeam,
} from '@/apis/getPrevChatByClient'
import { useWebSocket } from '@/components/providers/socket-provider'
import { ChatMessageType } from '@/types/ChatType'

import GroupChatLogList from '../chat-room/GroupChatLogList'
import GroupChatInputBox from './GroupChatInputBox'
import GroupChatHeader from './GroupChatHeader'

import styles from './chat.module.css'

export default function GroupChatRoomContainer({
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
      const readMsgData = await getReadMessageByTeam(roomNumber, token)
      const readMsg = readMsgData.result
      // 안읽은 메시지
      const unreadMsgData = await getUnreadMessageByTeam(roomNumber, token)
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
        `/sub/team/${roomNumber}`,
        (message) => {
          const payload = JSON.parse(message.body)
          setMsgLog((prev) => [...prev, payload])
        },
        {},
      )

      /** 채팅방 입장 */
      stompClient.send(
        `/pub/team/join/${roomNumber}`,
        {},
        JSON.stringify(message),
      )

      /** 채팅방 퇴장 */
      return () => {
        subscription.unsubscribe()
        stompClient.send(
          `/pub/team/exit/${roomNumber}`,
          {},
          JSON.stringify(message),
        )
      }
    }
    return undefined
  }, [stompClient])

  return (
    <div className={styles.chatroom}>
      <GroupChatHeader roomNumber={roomNumber} token={token} />

      <GroupChatLogList msgLog={msgLog} UUID={UUID} />

      <GroupChatInputBox roomNumber={roomNumber} UUID={UUID}>
        <GroupChatInputBox.IconBtn
          icon={<FileIcon />}
          handleClick={() => console.log('file')}
        />
        <GroupChatInputBox.IconBtn
          icon={<EmojiIcon />}
          handleClick={() => console.log('emoji')}
        />
      </GroupChatInputBox>
    </div>
  )
}
