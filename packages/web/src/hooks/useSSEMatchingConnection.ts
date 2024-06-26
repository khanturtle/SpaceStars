import { useState, useEffect } from 'react'
import { EventSourcePolyfill } from 'event-source-polyfill'

export const useSSEMatchingConnection = (uuid: string, token: string) => {
  const [eventSource, setEventSource] = useState<EventSourcePolyfill | null>(
    null,
  )
  const [isConnected, setIsConnected] = useState(false)
  const [showErrorModal, setShowErrorModal] = useState(false)

  useEffect(() => {
    const connectToSSE = () => {
      if (!isConnected) {
        import('event-source-polyfill')
          .then(({ EventSourcePolyfill }) => {
            const es = new EventSourcePolyfill(
              `${process.env.NEXT_PUBLIC_API_URL_V1}/sse-quick`,
              {
                headers: {
                  UUID: uuid,
                  Authorization: token,
                  'Content-Type': 'application/json',
                },
              },
            )

            es.onopen = () => {
              console.log('SSE connection opened successfully')
              setIsConnected(true)
            }

            es.onmessage = (event) => {
              console.log(event.data)
            }

            es.onerror = (error) => {
              console.error('EventSource failed:', error)
              es.close()
              setIsConnected(false)
              setShowErrorModal(true)
            }

            setEventSource(es)
          })
          .catch((error) => {
            console.error('Failed to load EventSourcePolyfill:', error)
            setIsConnected(false)
            setShowErrorModal(true)
          })
      }
    }

    connectToSSE()

    return () => {
      if (eventSource) {
        eventSource.close()
        setEventSource(null)
        setIsConnected(false)
      }
    }
  }, [isConnected, uuid])

  return { eventSource, isConnected, showErrorModal, setShowErrorModal }
}
