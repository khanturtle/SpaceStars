import MessageItem from './MessageItem'

export default function MessageContainer({
  children,
}: {
  children?: React.ReactNode
}) {
  return (
    <section className="mb-9">
      <h3 className="text-[#161616] text-lg not-italic font-medium leading-[normal] mb-6">
        Messages
      </h3>

      <div className="flex flex-col gap-2 scroll-none">{children}</div>
    </section>
  )
}

MessageContainer.Item = MessageItem
