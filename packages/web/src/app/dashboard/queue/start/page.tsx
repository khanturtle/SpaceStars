import { getServerSession } from 'next-auth/next'

import { getMbtiById } from '@/apis/getMbti'

import { options } from '@/app/api/auth/[...nextauth]/options'

import QueueLayout from '@/containers/queue/QueueLayout'

import { getAllProfileData } from '@/lib/getAllProfileData'

export default async function page({
  searchParams,
}: {
  searchParams: { [key: string]: string }
}) {
  const session = await getServerSession(options)
  const uuid = session?.user?.data.uuid

  // 내 정보 가져오기
  const myData = await getAllProfileData()
  let myMbtiName = ''

  if (myData.profileInfo?.mbtiId) {
    const res = await getMbtiById(myData.profileInfo.mbtiId)
    myMbtiName = res?.mbtiName ?? ''
  }

  const connectedGame = searchParams.game
  return (
    <div className="relative flex items-center justify-center w-full h-full">
      <QueueLayout
        myData={myData ?? null}
        mbtiName={myMbtiName}
        connectedGame={connectedGame}
        uuid={uuid}
      />
    </div>
  )
}
