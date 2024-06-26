'use client';

import React, { useEffect, useState } from 'react';

// Message 타입 정의
interface Message {
  senderUuid: string;
  receiverUuid: string;
  content: string;
}

const SseComponent: React.FC = () => {
  const [messages, setMessages] = useState<Message[]>([]);
  const uuid = '65459ea6-38f6-4789-b6a8-3ef476420164'; // UUID 값을 설정

  useEffect(() => {
    if (typeof window !== 'undefined') {
      // 동적 임포트를 사용하여 EventSourcePolyfill을 로드
      import('event-source-polyfill').then(({ EventSourcePolyfill }) => {
        const eventSource = new EventSourcePolyfill('https://spacestars.kr/api/v1/sse', {
          headers: {
            'UUID': uuid,
          },
          heartbeatTimeout: 86400000
        });

        // 연결 성공 시 로그 출력
        eventSource.onopen = () => {
          console.log('SSE connection opened successfully');
        };

        // 메시지 수신 시 처리
        eventSource.onmessage = (event) => {
          const newMessage: Message = JSON.parse(event.data);
          setMessages(prevMessages => [...prevMessages, newMessage]);
        };

        // 에러 처리
        eventSource.onerror = (error) => {
          console.error('EventSource failed:', error);
          eventSource.close();
        };

        // 컴포넌트 언마운트 시 SSE 연결 닫기
        return () => {
          eventSource.close();
        };
      }).catch(error => {
        console.error('Failed to load EventSourcePolyfill:', error);
      });
    }
  }, [uuid]);

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
