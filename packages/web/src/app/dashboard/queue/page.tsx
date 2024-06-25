import BackGroundTextBox from '@/containers/queue/BackGroundTextBox'
import NavHeader from '@/components/Navbar/NavHeader'
import QueueLayout from '@/containers/queue/QueueLayout'

export default function page() {
  return (
    // FIXME: 배경 수정
    <div className="relative flex-1 bg-[#18243a]">
      <BackGroundTextBox text="GAMER SEARCHING" />
      <NavHeader
        title="QUEUE"
        description="게임을 같이 할 나의 친구를 찾아 드려요!"
      />
      <QueueLayout />
    </div>
  )
}
