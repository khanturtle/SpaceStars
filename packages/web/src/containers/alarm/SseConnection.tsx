'use client';

import React, { useEffect, useRef, useState } from 'react';
import { EventSourcePolyfill } from 'event-source-polyfill';

// Message 타입 정의
interface Message {
  senderUuid: string;
  receiverUuid: string;
  content: string;
}

interface SseConnectionProps {
  uuid?: string
  onMessageReceived: () => void
}

const SseConnection: React.FC<SseConnectionProps> = ({uuid, onMessageReceived}) => {
  const [messages, setMessages] = useState<Message[]>([]);
  const eventSourceRef = useRef<EventSource | null>(null);

  useEffect(() => {
    // SSE 연결 함수
    const connectSSE = () => {
      if (uuid && typeof window !== 'undefined' && !eventSourceRef.current) {
        eventSourceRef.current = new EventSourcePolyfill('https://spacestars.kr/api/v1/sse', {
          headers: {
            'UUID': uuid,
          },
          heartbeatTimeout: 86400000,
        }) as unknown as EventSource;

        eventSourceRef.current.onopen = () => {
          console.log('SSE connection opened successfully');
        };

        eventSourceRef.current.onmessage = (event: any) => {
          const newMessage: Message = JSON.parse(event.data);
          console.log('New message received:', newMessage); 
          setMessages(prevMessages => [...prevMessages, newMessage]);
          onMessageReceived();
        };

        eventSourceRef.current.onerror = (error: any) => {
          console.error('EventSource failed:', error);
          eventSourceRef.current?.close();
          eventSourceRef.current = null; 
        };
      }
    };

    // 기존 연결 정리
    if (eventSourceRef.current) {
      eventSourceRef.current.close();
      eventSourceRef.current = null;
    }

    // 새로운 연결 시도
    connectSSE();

    return () => {
      if (eventSourceRef.current) {
        eventSourceRef.current.close();
        eventSourceRef.current = null;
      }
    };
  }, [uuid, onMessageReceived]);

  return null; 
};

export default SseConnection;
