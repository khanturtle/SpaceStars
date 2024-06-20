import { getProfileByUuid } from '@/apis/getAuth'
import { getChatRooms, getRecentMessage } from '@/apis/chat'
import { getMainProfileImageByUuid } from '@/apis/getProfileImage'

import { RoomInfoType } from '@/types/ChatType'

import MessageContainer from '@/containers/chat/MessageContainer'
import { defaultImage } from '@/store/defaultState'
import { getFriendsDataList } from '@/lib/getFriendsData'

// TODO: 친구 리스트 가져오기

async function getRoomInfo(
  roomNumber: string,
  peerUuid: string,
): Promise<RoomInfoType> {
  // 상대방 이름 / 메인 프로필 사진
  const peerProfileData = getProfileByUuid(peerUuid)
  const peerProfileImageData = getMainProfileImageByUuid(peerUuid)

  // 최근 메시지 static 조회
  const recentMessageData = getRecentMessage(roomNumber)

  const [peerProfile, peerProfileImage, recentMessage] = await Promise.all([
    peerProfileData,
    peerProfileImageData,
    recentMessageData,
  ])
  console.log('chat-recent-message', recentMessage)

  return {
    peerName: peerProfile?.result.nickname,
    peerProfileImage: peerProfileImage?.result.profileImageUrl ?? defaultImage,
    recentMessage: recentMessage ?? undefined,
  }
}

export default async function layout({
  children,
}: {
  children: React.ReactNode
}) {
  const friendsList = await getFriendsDataList()

  // 1:1 방 목록
  const rooms = await getChatRooms()
  console.log(rooms)

  const roomInfos = await Promise.all(
    rooms.map(async (room) => {
      const roomInfo = await getRoomInfo(room.roomNumber, room.otherMemberUuid)
      return {
        ...room,
        ...roomInfo,
      }
    }),
  )

  return (
    <section className="relative flex flex-row w-full h-full">
      <MessageContainer friendsList={friendsList} />

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
