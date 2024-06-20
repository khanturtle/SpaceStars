import RightSidebar from '@/components/Sidebar/RightSidebar'
import Sidebar from '@/components/Sidebar/Sidebar'

import { getServerSession } from 'next-auth/next'

import { options } from '@/app/api/auth/[...nextauth]/options'
import { getFriendsList } from '@/apis/getFriends'
import { getBasicUserData } from '@/lib/getUserData'

async function getFriendsDataList() {
  const friendsListResult = await getFriendsList()
  const friendsList = friendsListResult?.result
  if (!friendsList) return []

  const friendsWithBasicData = await Promise.all(
    friendsList.map(async (friend) => {
      const data = await getBasicUserData(friend.friendUuid)
      return { ...friend, ...data }
    }),
  )

  return friendsWithBasicData
}

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
