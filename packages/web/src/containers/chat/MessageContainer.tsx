import { TmpMsgList } from '@/apis/chat'
import Image from 'next/image'
import styles from './chat.module.css'

export default function MessageContainer({
  children,
}: {
  children?: React.ReactNode
}) {
  return (
    <div className="mb-9">
      <h3 className="text-[#161616] text-lg not-italic font-medium leading-[normal] mb-6">
        Messages
      </h3>

      {children}
    </div>
  )
}

const MessageItem = ({ item }: { item: TmpMsgList }) => {
  const isRead = item.unreadMessages > 0

  return (
    <li className={`flex items-center gap-4 ${styles.li}`}>
      <div className="relative w-12 h-12">
        <Image
          className="w-12 h-12 rounded-full"
          width={48}
          height={48}
          src={item.avatar}
          alt={item.name}
        />
        {/* FIXME: 색깔 수정 및 등장 조건 */}
        <div className="w-3 h-3 shrink-0 bg-[color:var(--online,#3bcd23)] border border-[color:var(--White-50,#fff)] absolute rounded-full right-0 bottom-0" />
      </div>

      <div className="flex-1">
        <div className={`mb-1 flex items-start justify-between`}>
          <h4 className="text-[#161616] text-base not-italic font-semibold leading-[normal]">
            {item.name}
          </h4>
          <span className="text-[#869aa9] text-right text-[10px] not-italic font-normal leading-[normal]">
            {item.time}
          </span>
        </div>
        <div className="flex items-center justify-between">
          <p
            className={`text-ellipsis whitespace-nowrap overflow-hidden ${isRead ? 'text-[#161616]' : 'text-[#869AA9]'} text-xs not-italic font-normal leading-[normal] max-w-[160px]`}
          >
            {item.message}
          </p>
          {item.unreadMessages > 0 && (
            <div className="w-[23px] h-6 bg-[#f15c45] flex items-center justify-center rounded-lg">
              <p className="text-[color:var(--White-50,#fff)] text-xs not-italic font-semibold leading-[normal]">
                {item.unreadMessages}
              </p>
            </div>
          )}
        </div>
      </div>
    </li>
  )
}

const MessageList = ({ messages }: { messages: TmpMsgList[] }) => {
  return (
    <ul className={styles.ul}>
      {messages.map((item) => (
        <MessageItem key={item.index} item={item} />
      ))}
    </ul>
  )
}

MessageContainer.MessageList = MessageList
