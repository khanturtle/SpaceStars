'use client'

import React, {
  createContext,
  useContext,
  useEffect,
  useState,
  useCallback,
} from 'react'
import type { ReactNode } from 'react'

import { Stomp } from '@stomp/stompjs'
import SockJS from 'sockjs-client'

import type { CompatClient } from '@stomp/stompjs'

// WebSocket 컨텍스트 타입
type WebSocketContextType = {
  stompClient: CompatClient | null
  messages: string[]
  sendMessage: (message: string) => void
}

const WebSocketContext = createContext<WebSocketContextType | undefined>(
  undefined,
)

export function useWebSocket(): WebSocketContextType | undefined {
  return useContext(WebSocketContext)
}

type WebSocketProviderProps = {
  children: ReactNode
}

function WebSocketProvider({ children }: WebSocketProviderProps) {
  const [stompClient, setStompClient] = useState<CompatClient | null>(null)
  const [messages, setMessages] = useState<string[]>([])

  useEffect(() => {
    const socket = new SockJS('http://localhost:8087/api/v1/wschat')
    const client = Stomp.over(socket)
    client.debug = () => {}

    client.connect(
      {},
      (frame) => {
        console.log('Connected to WebSocket:', frame)
        setStompClient(client)

        // 입장 메시지를 서버로 전송
        const chatJoinReqVo = {
          senderUuid: 'local-join-test',
        }
        client.send(`/send/join/1`, {}, JSON.stringify(chatJoinReqVo))

        client.subscribe(`/room/1`, (message) => {
          const newMessage = JSON.parse(message.body)
          setMessages((prevMessages) => [...prevMessages, newMessage])
        })
      },
      (error) => {
        console.error('WebSocket connection error:', error)
      },
    )

    return () => {
      client.disconnect(() => {
        console.log('연결 끊김')
      })

      if (client) {
        client.disconnect(() => {
          const chatExitReqVo = {
            senderUuid: 'local-exit-test',
          }
          client.send(`/send/exit/1`, {}, JSON.stringify(chatExitReqVo))
          console.log('WebSocket disconnected')
        })
      }
    }
  }, [])

  const sendMessage = useCallback(
    (message: string) => {
      if (stompClient && message.trim() !== '') {
        const chatMessage = {
          senderUuid: 'uuid-string-other',
          content: message,
          messageType: 'TEXT',
        }
        stompClient.send('/send/1', {}, JSON.stringify(chatMessage)) //  1은 roonNumber로 대체하면댐
      }
    },
    [stompClient],
  )

  return (
    <WebSocketContext.Provider value={{ stompClient, messages, sendMessage }}>
      {children}
    </WebSocketContext.Provider>
  )
}

function ChatComponent() {
  const { messages, sendMessage } = useWebSocket() || {}
  const [messageInput, setMessageInput] = useState('')

  const handleSendMessage = () => {
    if (sendMessage && messageInput.trim() !== '') {
      sendMessage(messageInput)
      setMessageInput('')
    }
  }

  return (
    <div>
      <div>
        {messages?.map((message, index) => (
          <div key={index}>
            {message.senderUuid}: {message.content}
          </div>
        ))}
      </div>
      <input
        type="text"
        value={messageInput}
        onChange={(e) => setMessageInput(e.target.value)}
      />
      <button type="button" onClick={handleSendMessage}>
        Send
      </button>
    </div>
  )
}

function App() {
  return (
    <WebSocketProvider>
      <ChatComponent />
    </WebSocketProvider>
  )
}

export default App
