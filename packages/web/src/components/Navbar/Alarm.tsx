'use client';

import { useState, useEffect, useRef } from 'react'; 
import { useSession } from 'next-auth/react'; 
import { BellIcon } from 'lucide-react';
import AlarmListContainer from '@/containers/alarm/AlarmListContainer'; 

export const Alarm = () => {
  const alarmListRef = useRef();
  const { data: session } = useSession();
  const [token, setToken] = useState();

  useEffect(() => {
    if (session) {
      const _token = session.user?.data.accessToken;
      setToken(_token);
    }
  }, [session]);

  const handleClick = () => {
    alarmListRef.current.openAlarmModal();
  };

  return (
    <div className="flex items-center">
      <div className="relative mr-8">
        <button type="button" onClick={handleClick}>
          <BellIcon size={40} stroke="white" strokeWidth={2} />
        </button>
        <span className="absolute top-0 right-0 translate-x-1/2 -translate-y-1/2 bg-red-500 rounded-full w-3 h-3"></span>
      </div>
      <AlarmListContainer ref={alarmListRef} accessToken={token} />
    </div>
  );
};

export default Alarm;