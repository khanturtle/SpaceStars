import RightSidebar from '@/components/Sidebar/RightSidebar'
import Sidebar from '@/components/Sidebar/Sidebar'

import { getFriendsDataList } from '@/lib/getFriendsData'

export default async function layout({
  children,
}: {
  children: React.ReactNode
}) {
  // const session = await getServerSession(options)

  // console.log("여긴 대시보드 레이아웃", session)

  // 내 친구 리스트 불러오기
  const friendsList = await getFriendsDataList()

  return (
    <main className="flex w-full h-[calc(100vh_-_100px)] overflow-hidden mx-auto my-0">
      <Sidebar />
      {children}
      <RightSidebar friendsList={friendsList ? friendsList : []} />
    </main>
  )
}
