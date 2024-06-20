import MessageItem from './MessageItem'

export default function MessageWrapper({
  chatRoomList,
}: {
  chatRoomList: any[]
}) {
  console.log(chatRoomList)

  return (
    <section className="mb-9">
      <h3 className="text-[#161616] text-lg not-italic font-medium leading-[normal] mb-6">
        Messages
      </h3>

      <div className="flex flex-col gap-2 scroll-none">
        {chatRoomList.map((chatRoom) => (
          <MessageItem key={chatRoom.index} room={chatRoom} />
        ))}
      </div>
    </section>
  )
}
