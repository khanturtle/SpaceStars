import BackGroundTextBox from '@/containers/queue/BackGroundTextBox'
import QueueHeader from '@/containers/queue/QueueHeader'
import QueueLayout from '@/containers/queue/QueueLayout'

export default function page() {
  return (
    <div className="relative w-full bg-[#18243a]">
      <BackGroundTextBox text="GAMER SEARCHING" />
      <QueueHeader />
      <QueueLayout />
    </div>
  )
}
