'use client';

import {useContext, useEffect, useState} from 'react';
import {ModalContext} from '@/components/providers/modal-provider'
import FormLayout from '@/components/form/formLayout'
import { PlusIcon } from 'lucide-react'
import { getAlarms } from '@/apis/getAlarms';
import { AlarmListType } from '@/types/type';
import { useSession } from 'next-auth/react';

export default function Page() {
    const {openModal} = useContext(ModalContext)
    const [alarms, setAlarms] = useState<AlarmListType[]>([]);
    const {data:session} = useSession()
    const [token, setToken] = useState()

    useEffect(() => {
      if(session){
        const _token = session.user?.data.accessToken
        setToken(_token)
      }
    },[])
    //const token = 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1dWlkIjoiNDRhNTM0ZmUtZmU1Zi00MTk4LTlmMTItZDExODYxMTdiZmQ3Iiwicm9sZSI6IlJPTEVfVVNFUiIsImlhdCI6MTcxOTQ3NjQxOCwiZXhwIjoxNzIyMDY4NDE4fQ._dztgiWUroRqHj0pQGiVjFAXke0F_ChnkZPCme60wXg';
    
    useEffect(() => {
      const fetchAlarms = async () => {
        const data = await getAlarms();
        if(data){
          setAlarms(data.result);
        }
      };
      fetchAlarms();
    }, [token]);

    const handleClick = () => {
        openModal(
            <div className={`relative h-full flex flex-col items-center`}>
                <FormLayout
                    className="relative h-full px-[204px] pt-[90px] pb-[85px]
                flex flex-col items-center">
                    <FormLayout.Legend
                        title="알림 목록"
                    />
                    <div className='flex flex-col items-center'>

                      {alarms && alarms.length > 0 ? (
                        alarms.map((alarm) => (
                          <div key={alarm.index} className="mb-2 p-2 border rounded">
                            <p><strong>Sender UUID:</strong> {alarm.senderUuid}</p>
                            <p><strong>Check Status:</strong> {alarm.checkStatus}</p>
                            <p><strong>Alarm Type:</strong> {alarm.alarmType}</p>
                            <p><strong>Content:</strong> {alarm.content}</p>
                          </div>
                        ))
                      ):(
                        <p>새로운 알림이 없습니다.</p>
                      )}
                    </div>
                </FormLayout>
            </div>,
        )
    }

    return (
      <button
        type="button"
        onClick={handleClick}
      >
        <PlusIcon strokeWidth={2.5}/>
      </button>
  )
}