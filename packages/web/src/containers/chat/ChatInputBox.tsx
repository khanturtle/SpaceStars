import { useWebSocket } from '@/components/providers/socket-provider'
import { SendIcon } from '@packages/ui'
import { ChangeEvent, useState } from 'react'

const InputItem = ({
  roomNumber,
  UUID,
}: {
  roomNumber: string
  UUID: string
}) => {
  const [chat, setChat] = useState<string>('')

  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    setChat(e.target.value)
  }

  const stompClient = useWebSocket()

  /** 메시지 전송 */
  // FIXME: url 수정
  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault()
    // console.log(chat, stompClient)
    if (chat !== '' && stompClient) {
      stompClient.send(
        `/pub/one-to-one/${roomNumber}`,
        {},
        JSON.stringify({
          senderUuid: UUID,
          content: chat,
          messageType: 'TEXT',
        }),
      )
      setChat('')
    }
  }

  return (
    <form
      className="flex flex-1 justify-between gap-3.5 pl-2.5"
      onSubmit={handleSubmit}
    >
      <input
        className="inline-block w-full flex-grow-1 bg-[color:var(--White-50,#fff)] text-[#161616] text-sm not-italic font-normal leading-[normal] px-5 py-2 input-reset chat-placeholder"
        type="text"
        placeholder="Write a message..."
        value={chat}
        onChange={handleChange}
        autoComplete="off"
        autoFocus
      />

      <button type="submit">
        <i
          className="flex items-center justify-center rounded-full w-9 h-9"
          style={{
            background: 'linear-gradient(156deg, #9e77ff 0.66%, #4c12dc 99.5%)',
          }}
        >
          <SendIcon />
        </i>
      </button>
    </form>
  )
}

export default function ChatInputBox({
  children,
  roomNumber,
  UUID,
}: {
  children: React.ReactNode
  roomNumber: string
  UUID: string
}) {
  return (
    <div className="bg-[color:var(--White-50,#fff)] w-full h-[90px] flex items-center relative px-[50px] py-4">
      {children}

      <InputItem roomNumber={roomNumber} UUID={UUID} />
    </div>
  )
}

const IconBtn = ({
  icon,
  handleClick,
}: {
  icon?: React.ReactNode
  handleClick: () => void
}) => {
  return (
    <div className="flex items-center justify-center w-12 h-12">
      <button onClick={handleClick}>
        <i className="w-6 h-6">{icon}</i>
      </button>
    </div>
  )
}

ChatInputBox.IconBtn = IconBtn
