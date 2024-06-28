'use client';

import { useState, useEffect, useRef } from 'react'; 
import { useSession } from 'next-auth/react'; 
import { BellIcon } from 'lucide-react';
import AlarmListContainer from '@/containers/alarm/AlarmListContainer'; 
import SseConnection from '@/containers/alarm/SseConnection';

export const Alarm = () => {
  const alarmListRef = useRef();
  const { data: session } = useSession();
  const [token, setToken] = useState<string | undefined>();
  const [uuid, setUuid] = useState<string | undefined>();
  const [newMessageReceived, setNewMessageReceived] = useState(false);

  useEffect(() => {
    if (session) {
      setToken(session.user?.data.accessToken);
      setUuid(session.user?.data.uuid);
    }
  }, [session]);

  const handleClick = () => {
    if (alarmListRef.current) {
      alarmListRef.current.openAlarmModal();
    }
    setNewMessageReceived(false); 
  };

  return (
    <div className="flex items-center">
      <div className="relative mr-8">
        <button type="button" onClick={handleClick}>
          <BellIcon size={40} stroke="white" strokeWidth={2} />
          {newMessageReceived && (
            <span className="absolute top-0 right-0 translate-x-1/2 -translate-y-1/2 bg-red-500 rounded-full w-3 h-3"></span>
          )}
        </button>
      </div>
      {uuid && <SseConnection uuid={uuid} onMessageReceived={() => setNewMessageReceived(true)} />}
      <AlarmListContainer ref={alarmListRef} accessToken={token} />
    </div>
  );
};

export default Alarm;