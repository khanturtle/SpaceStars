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
  const [stompClient, setStompClient] = useState<
    WebSocketContextType | undefined
  >(undefined)

  useEffect(() => {
    const socket = new SockJS(`${process.env.NEXT_PUBLIC_API_URL_V1}/wschat`)
    const client = Stomp.over(() => socket)
    client.debug = () => {}

    function connect() {
      client.connect({}, function connection() {
        setStompClient(client)
      })
    }

    connect()

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
