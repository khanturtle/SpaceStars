import { getServerSession } from 'next-auth/next'

import { options } from '@/app/api/auth/[...nextauth]/options'

import MessageContainer from '@/containers/chat/MessageContainer'
import { getFriendsDataList } from '@/lib/getFriendsData'
import {
  getChatroomDataList,
  getGroupChatroomData,
} from '@/lib/getChatroomData'

export default async function layout({
  children,
}: {
  children: React.ReactNode
}) {
  const session = await getServerSession(options)
  const token = session?.user?.data.accessToken

  // 친구 리스트
  const friendsList = await getFriendsDataList()
  // 1:1 방 목록
  const oneToOneChatRooms = await getChatroomDataList()
  // 그룹채팅 방 목록
  const groupChatRooms = await getGroupChatroomData()

  return (
    <section className="relative flex flex-row w-full h-full">
      <MessageContainer
        friendsList={friendsList}
        oneToOneChatRooms={oneToOneChatRooms}
        groupChatRooms={groupChatRooms}
        token={token}
      />

      {children}
    </section>
  )
}
