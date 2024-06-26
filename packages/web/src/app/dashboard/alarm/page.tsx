'use client';

import React, { useEffect, useState, useRef } from 'react';
import { EventSourcePolyfill } from 'event-source-polyfill';

// Message 타입 정의
interface Message {
  senderUuid: string;
  receiverUuid: string;
  content: string;
}

const SseComponent: React.FC = () => {
  const [messages, setMessages] = useState<Message[]>([]);
  const eventSourceRef = useRef<EventSource | null>(null);
  const uuid = '4258cc84-d993-4235-8abd-5ca8853483bb'; // UUID 값을 설정

  useEffect(() => {
    if (typeof window !== 'undefined' && !eventSourceRef.current) {
      eventSourceRef.current = new EventSourcePolyfill('https://spacestars.kr/api/v1/sse', {
        headers: {
          'UUID': uuid,
        },
        heartbeatTimeout: 86400000,
      }) as unknown as EventSource; // 타입을 명시적으로 설정

      // 연결 성공 시 로그 출력
      eventSourceRef.current.onopen = () => {
        console.log('SSE connection opened successfully');
      };

      // 메시지 수신 시 처리
      eventSourceRef.current.onmessage = (event: any) => {
        const newMessage: Message = JSON.parse(event.data);
        setMessages(prevMessages => [...prevMessages, newMessage]);
      };

      // 에러 처리
      eventSourceRef.current.onerror = (error: any) => {
        console.error('EventSource failed:', error);
        eventSourceRef.current?.close();
      };
    }

    // 컴포넌트 언마운트 시 SSE 연결 닫기
    return () => {
      if (eventSourceRef.current) {
        eventSourceRef.current.close();
        eventSourceRef.current = null;
      }
    };
  }, []); // 빈 배열로 설정하여 한 번만 실행되도록 함

  return (
    <div>
      <h2>알림 목록</h2>
      <ul>
        {messages.map((message, index) => (
          <li key={index}>
            {message.senderUuid} {'->'} {message.receiverUuid}: {message.content}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default SseComponent;