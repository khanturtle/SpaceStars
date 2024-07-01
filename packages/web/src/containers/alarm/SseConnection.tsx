'use client'

import { useEffect } from 'react'
import { EventSourcePolyfill } from 'event-source-polyfill'
import { closeSseConnection } from '@/apis/actionAlarm'

// Message 타입 정의
interface Message {
  senderUuid: string
  receiverUuid: string
  content: string
}

interface SseComponentProps {
  uuid: string
  onMessage: (message: Message) => void
}

const SseComponent: React.FC<SseComponentProps> = ({ uuid, onMessage }) => {
  useEffect(() => {
    const eventSource = new EventSourcePolyfill(
      'https://spacestars.kr/api/v1/sse',
      {
        headers: { UUID: uuid },
        heartbeatTimeout: 86400000,
      },
    )

    eventSource.onopen = () => {
      // console.log('SSE 연결이 성공적으로 열렸습니다.');
    }

    eventSource.onmessage = (event: any) => {
      const newMessage: Message = JSON.parse(event.data)
      //   console.log('Received data:', newMessage)
      onMessage(newMessage)
    }

    return () => {
      eventSource.close()
      closeSseConnection(uuid)
    }
  }, [uuid, onMessage])

  return null
}

export default SseComponent
