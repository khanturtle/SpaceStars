'use clinet';

import React, { useEffect, useRef, useState } from 'react';
import { EventSourcePolyfill } from 'event-source-polyfill';

// Message 타입 정의
interface Message {
  senderUuid: string;
  receiverUuid: string;
  content: string;
}

const SseConnection: React.FC = () => {
  const [messages, setMessages] = useState<Message[]>([]);
  const eventSourceRef = useRef<EventSource | null>(null);
  const uuid = '44a534fe-fe5f-4198-9f12-d1186117bfd7'; // UUID 값을 설정
  const accessToken = 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1dWlkIjoicXJzMzQ1Iiwicm9sZSI6IlJPTEVfVVNFUiIsImlhdCI6MTcxOTQ1NDQ0MywiZXhwIjoxNzIyMDQ2NDQzfQ.TKkeuNCbCTu53njF15WvMVJ16gE3Q69Rizi4gBklb4c';

  useEffect(() => {
    if (typeof window !== 'undefined' && !eventSourceRef.current) {
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
      };

      eventSourceRef.current.onerror = (error: any) => {
        console.error('EventSource failed:', error);
        eventSourceRef.current?.close();
      };
    }

    return () => {
      if (eventSourceRef.current) {
        eventSourceRef.current.close();
        eventSourceRef.current = null;
      }
    };
  }, []);

  return null; // 이 컴포넌트는 UI를 렌더링하지 않습니다.
};

export default SseConnection;
