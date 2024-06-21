import { headers } from 'next/headers'

import RightSidebar from '@/components/Sidebar/RightSidebar'
import Sidebar from '@/components/Sidebar/Sidebar'

import { getFriendsDataList } from '@/lib/getFriendsData'
import { getChatroomData, MemberInfoType } from '@/lib/getChatroomData'

export default async function layout({
  children,
}: {
  children: React.ReactNode
}) {
  const headersList = headers()
  const headerPathname = headersList.get('x-pathname') || ''
  const isChatPage = headerPathname.includes('chat')
  const roomNumber = isChatPage ? headerPathname.split('/').at(-1) : ''

  let memberInfos: MemberInfoType[] = []
  // 채팅방 멤버 리스트
  if (isChatPage && roomNumber && roomNumber !== 'chat') {
    memberInfos = await getChatroomData(roomNumber)
  }

  // 내 친구 리스트
  const friendsList = await getFriendsDataList()

  return (
    <main className="flex w-full h-[calc(100vh_-_100px)] overflow-hidden mx-auto my-0">
      <Sidebar />
      {children}
      <RightSidebar
        isChatPage={isChatPage}
        friendsList={friendsList ? friendsList : []}
        memberInfos={memberInfos}
      />
    </main>
  )
}
