import { SendIcon } from '@packages/ui'
import { ChangeEvent, useState } from 'react'
import styles from './chat.module.css'

const InputItem = () => {
  const [chat, setChat] = useState<string>('')

  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    setChat(e.target.value)
  }

  /** 채팅 전송 */
  const handleClick = () => {
    console.log('Sending chat:', chat)
    setChat('')
  }

  return (
    <form className="flex justify-between grow gap-3.5 pl-2.5">
      <input
        className={`flex grow bg-[color:var(--White-50,#fff)] text-[#161616] text-sm not-italic font-normal leading-[normal] px-5 py-2 input-reset ${styles.input}`}
        type="text"
        placeholder="Write a message..."
        value={chat}
        onChange={handleChange}
        autoComplete="off"
        autoFocus
      />

      <button type="submit" onClick={handleClick}>
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
}: {
  children: React.ReactNode
}) {
  return (
    <div className={styles.chat}>
      {children}

      <InputItem />
    </div>
  )
}

const IconBtn = ({
  children,
  handleClick,
}: {
  children?: React.ReactNode
  handleClick: () => void
}) => {
  return (
    <div className="flex items-center justify-center w-12 h-12">
      <button onClick={handleClick}>
        <i className="w-6 h-6">{children}</i>
      </button>
    </div>
  )
}

ChatInputBox.IconBtn = IconBtn
