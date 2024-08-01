import { getServerSession } from 'next-auth/next'

import { options } from '../api/auth/[...nextauth]/options'

import RightSidebar from '@/components/Sidebar/RightSidebar'
import Sidebar from '@/components/Sidebar/Sidebar'

import { getFriendsDataList } from '@/lib/getFriendsData'

export default async function layout({
  children,
}: {
  children: React.ReactNode
}) {
  const session = await getServerSession(options)
  const token = session?.user?.data.accessToken

  // 내 친구 리스트
  const friendsList = await getFriendsDataList()

  if (!session) {
    return <div></div>
  }

  return (
    <main className="flex w-full h-[calc(100vh_-_100px)] overflow-hidden mx-auto my-0">
      <Sidebar />
      {children}
      <RightSidebar
        token={token}
        friendsList={friendsList ? friendsList : []}
      />
    </main>
  )
}
