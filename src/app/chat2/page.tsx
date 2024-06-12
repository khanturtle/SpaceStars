'use client'

import { ChatComponent, WebSocketProvider } from './Socket'

export default function page() {
  return (
    <WebSocketProvider>
      <ChatComponent />
    </WebSocketProvider>
  )
}
