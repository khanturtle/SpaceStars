'use client';

import React, { useEffect, useState, useRef } from 'react';
import { EventSourcePolyfill } from 'event-source-polyfill';

// Message 타입 정의
interface Message {
  senderUuid: string;
  receiverUuid: string;
  content: string;
}

// 알림 목록 타입 정의
interface Alarm{
  senderUuid: string;
  alarmType: string;
  content: string;
}

const SseComponent: React.FC = () => {
  const [messages, setMessages] = useState<Message[]>([]);
  const [alarms, setAlarms] = useState<Alarm[]>([]);
  const eventSourceRef = useRef<EventSource | null>(null);
  const uuid = '4258cc84-d993-4235-8abd-5ca8853483bb'; // UUID 값을 설정
  const accessToken = '';

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

    // 알림 목록 가져오기
    const fetchAlarms = async () => {
      try{
        const response = await fetch('https://spacestars.kr/api/v1/alarm/list', {
          method: 'GET',
          headers:{
            'Content_Type' : 'application/json',
            'Authorization' : accessToken
          }
        });

        if(!response.ok){
          throw new Error('Failed to fetch alarm list');
        }

        const data = await response.json();
        const alarmData = data.result.alarmList.map((item: any) => ({
          senderUuid: item.senderUuid,
          alarmType: item.alarmType,
          content: item.content
        }));
        setAlarms(alarmData);
      }catch(error){
        console.error('Failed to fetch alarm list: ', error);
      }
    };

    fetchAlarms();

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
        {alarms.map((alarms, index) => (
          <li key={index}>
            {alarms.senderUuid} {'->'}{alarms.alarmType}:{alarms.content}
          </li>
        ))} 
      </ul>
    </div>
  );
};

export default SseComponent;