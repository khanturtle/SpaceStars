'use client'

import {
  createContext,
  useContext,
  useEffect,
  useState,
  ReactNode,
} from 'react'
import SockJS from 'sockjs-client'
import { CompatClient, Stomp } from '@stomp/stompjs'

// WebSocket 컨텍스트 타입
type WebSocketContextType = CompatClient | null

const WebSocketContext = createContext<WebSocketContextType | undefined>(
  undefined,
)

export function useWebSocket(): WebSocketContextType | undefined {
  return useContext(WebSocketContext)
}

export default function WebSocketProvider({
  children,
}: {
  children: ReactNode
}) {
  const [stompClient, setStompClient] = useState<CompatClient | null>(null)

  useEffect(() => {
    const socket = new SockJS(`${process.env.NEXT_PUBLIC_API_URL_V1}/wschat`)
    const client = Stomp.over(() => socket)
    client.debug = () => {}

    client.connect(
      {},
      (frame: any) => {
        console.log('Connected to WebSocket:', frame)
        setStompClient(client)
      },
      (error: any) => {
        console.error('WebSocket connection error:', error)
      },
    )

    return () => {
      if (client) {
        client.disconnect()
      }
    }
  }, [])

  return (
    <WebSocketContext.Provider value={stompClient}>
      {children}
    </WebSocketContext.Provider>
  )
}
