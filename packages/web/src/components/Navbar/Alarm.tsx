'use client';

import { useState, useEffect, useRef } from 'react'; 
import { useSession } from 'next-auth/react'; 
import { BellIcon } from 'lucide-react';
import AlarmListContainer from '@/containers/alarm/AlarmListContainer'; 
import SseComponent from "@/containers/alarm/SseService";

interface AlarmListMethods {
  openAlarmModal: () => void;
}

export const Alarm = () => {
  const alarmListRef = useRef<AlarmListMethods>(null);
  const { data: session } = useSession();
  const [token, setToken] = useState<string | undefined>();
  const [uuid, setUuid] = useState<string | undefined>();
  const [hasNewNotification, setHasNewNotification] = useState<boolean>(false);
  const [refreshKey, setRefreshKey] = useState<number>(0);

  useEffect(() => {
    if (session) {
      setToken(session.user?.data.accessToken);
      setUuid(session.user?.data.uuid);
    }
  }, [session]);

  useEffect(() => {
    if (typeof window !== "undefined") {
      const storedNotification = localStorage.getItem('hasNewNotification');
      if (storedNotification !== null) {
        setHasNewNotification(JSON.parse(storedNotification));
      }
    }
  }, []);

  useEffect(() => {
    localStorage.setItem('hasNewNotification', JSON.stringify(hasNewNotification));
  }, [hasNewNotification]);

  const handleClick = () => {
    alarmListRef.current?.openAlarmModal();

    // 알림을 클릭하면 빨간 원 숨기기
    setHasNewNotification(false);
  };

  const handleNewMessage = (message: any) => {
    setRefreshKey((prevKey) => prevKey + 1);
    setHasNewNotification(true);
  }

  return (
    <div className="flex items-center">
      <div className="relative mr-8">
        <button type="button" onClick={handleClick}>
          <BellIcon size={40} stroke="white" strokeWidth={2} />
          {hasNewNotification &&
            <span className="absolute top-0 right-0 h-3 w-3 bg-red-500 rounded-full"></span>
          }
        </button>
      </div>
      {token && <AlarmListContainer 
        ref={alarmListRef} 
        accessToken={token} 
        refreshKey={refreshKey}
      />}
      {uuid && <SseComponent uuid={uuid} onMessage={handleNewMessage} />}
    </div>
  );
};

export default Alarm;