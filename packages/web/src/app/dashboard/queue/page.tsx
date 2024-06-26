import { getMbtiById } from '@/apis/getMbti'
import BackGroundTextBox from '@/components/Background/BackGroundTextBox'
import NavHeader from '@/components/Navbar/NavHeader'
import QueueLayout from '@/containers/queue/QueueLayout'
import { getAllProfileData } from '@/lib/getAllProfileData'

export default async function page() {
  // 내 정보 가져오기
  const myData = await getAllProfileData()
  let myMbtiName = ''

  if (myData.profileInfo?.mbtiId) {
    const res = await getMbtiById(myData.profileInfo.mbtiId)
    myMbtiName = res?.mbtiName ?? ''
  }

  return (
    // FIXME: 배경 수정
    <div className="relative flex-1 bg-[#18243a]">
      <BackGroundTextBox text="GAMER SEARCHING" />
      <NavHeader
        title="QUEUE"
        description="게임을 같이 할 나의 친구를 찾아 드려요!"
      />

      <QueueLayout myData={myData ?? null} mbtiName={myMbtiName} />
    </div>
  )
}
