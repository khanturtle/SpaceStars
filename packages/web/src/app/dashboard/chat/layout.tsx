import { getServerSession } from 'next-auth/next'

import { options } from '@/app/api/auth/[...nextauth]/options'

import { getProfileByUuid } from '@/apis/getAuth'
import { getChatRooms } from '@/apis/getChat'
import { getMainProfileImageByUuid } from '@/apis/getProfileImage'

import { RoomInfoType } from '@/types/ChatType'

import MessageContainer from '@/containers/chat/MessageContainer'
import { defaultImage } from '@/store/defaultState'
import { getFriendsDataList } from '@/lib/getFriendsData'
import { getChatroomDataList } from '@/lib/getChatroomData'

// async function getRoomInfo(
//   roomNumber: string,
//   peerUuid: string,
// ): Promise<RoomInfoType> {
//   // 최근 메시지 static 조회
//   const recentMessage = await getRecentMessage(roomNumber)

//   return recentMessage
// }

export default async function layout({
  children,
}: {
  children: React.ReactNode
}) {
  const session = await getServerSession(options)
  const token = session?.user?.data.accessToken

  const friendsList = await getFriendsDataList()

  // 1:1 방 목록
  const oneToOneChatRooms = await getChatroomDataList()

  return (
    <section className="relative flex flex-row w-full h-full">
      <MessageContainer
        friendsList={friendsList}
        oneToOneChatRooms={oneToOneChatRooms}
        token={token}
      />

      {/* <MessageContainer>
          {roomInfos.map((roomInfo) => {
            return (
              <MessageContainer.Item
                key={roomInfo.index}
                item={{
                  index: roomInfo.index,
                  roomNumber: roomInfo.roomNumber,
                  otherMemberUuid: roomInfo.otherMemberUuid,
                }}
                roomInfo={{
                  peerName: roomInfo.peerName,
                  peerProfileImage: roomInfo.peerProfileImage,
                  recentMessage: roomInfo.recentMessage,
                }}
              />
            )
          })}
        </MessageContainer> */}

      {children}
    </section>
  )
}
