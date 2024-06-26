import { getServerSession } from 'next-auth/next'

import { getMbtiById } from '@/apis/getMbti'

import BackGroundTextBox from '@/components/Background/BackGroundTextBox'
import QueueLayout from '@/containers/queue/QueueLayout'

import { getAllProfileData } from '@/lib/getAllProfileData'
import { options } from '@/app/api/auth/[...nextauth]/options'

export default async function page({
  searchParams,
}: {
  searchParams: { [key: string]: string }
}) {
  const session = await getServerSession(options)
  const uuid = session?.user?.data.uuid
  const token = session?.user?.data.accessToken

  // 내 정보 가져오기
  const myData = await getAllProfileData()
  let myMbtiName = ''

  if (myData.profileInfo?.mbtiId) {
    const res = await getMbtiById(myData.profileInfo.mbtiId)
    myMbtiName = res?.mbtiName ?? ''
  }

  const connectedGame = searchParams.game

  return (
    // FIXME: 배경 수정
    <div className="relative w-full h-full flex items-center justify-center bg-[#18243a]">
      <BackGroundTextBox text="GAMER SEARCHING" />

      <QueueLayout
        myData={myData ?? null}
        mbtiName={myMbtiName}
        connectedGame={connectedGame}
        uuid={uuid}
        token={token}
      />
    </div>
  )
}
