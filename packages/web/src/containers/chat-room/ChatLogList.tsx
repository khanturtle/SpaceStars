import MessageGroup from './MessageGroup'
import { ChatMessageType } from '@/types/ChatType'

export default function ChatLogList({
  msgLog,
  UUID,
}: {
  msgLog: ChatMessageType[]
  UUID: string
}) {
  /** senderUuid를 기준으로 그룹화  */
  const groupedMessages = msgLog.reduce((groups, msg) => {
    const lastGroup = groups[groups.length - 1]

    if (lastGroup && lastGroup[0].senderUuid === msg.senderUuid) {
      // 이전 그룹과 senderUuid가 같으면 마지막 배열에 추가
      lastGroup.push(msg)
    } else {
      // 새로운 그룹 생성
      groups.push([msg])
    }

    return groups
  }, [] as ChatMessageType[][])

  return (
    <section className="flex flex-col w-full h-[calc(100%_-_90px)] overflow-y-scroll bg-[#f8f8f9] px-[50px] py-[24px]">
      {groupedMessages.map((messages) => (
        <MessageGroup
          key={messages[0].createdAt}
          messages={messages}
          UUID={UUID}
        />
      ))}
    </section>
  )
}
