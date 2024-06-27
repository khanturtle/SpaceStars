'use client'

import React, {useState} from 'react'
import { BellIcon } from 'lucide-react'
import SseConnection from '@/containers/alarm/SseConnection'

export const Alarm = () => {

  const [isConnected, setIsConnected] = useState(false);
  
  const handleClick = () => {
    console.log('alarm clicked')
    setIsConnected(true); // SSE 연결 활성화
  }
  return (
    <div className="flex items-center">
      <button type="button" onClick={handleClick} className="mr-8">
        <BellIcon size={40} stroke="white" strokeWidth={2} />
      </button>
      {isConnected && <SseConnection />}
    </div>
  )
}