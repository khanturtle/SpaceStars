import { getProfileByUuid } from '@/apis/getAuth'
import { getChatRooms, getRecentMessage } from '@/apis/chat'
import { TmpFriendType } from '@/apis/getFriends'
import { getMainProfileImageByUuid } from '@/apis/getProfileImage'

import { RoomInfoType } from '@/types/ChatType'

import MessageContainer from '@/containers/chat/MessageContainer'
import SearchBox from '@/containers/chat/SearchBox'
import OnlineFriends from '@/components/Friends/OnlineFriends'
import { defaultImage } from '@/store/defaultState'

// TODO: 친구 리스트 가져오기
const tmpImage =
  'https://s3-alpha-sig.figma.com/img/9940/be86/a995f084b85137bcc0c1992f82233743?Expires=1719187200&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=iCRYnSw1pQ4EkjZdA0lD9gM-Cc3KFthEoBwSpE7~Jrjv8eXWalTlqxgc~-sQl9IrOH9w45DAt1rIQo5XRsvz8mrk7NX0lKqEZse9JH8FJp~~3NSbXCejQAkZcIiiaUqKWPpGKvi7C2v1ne0NQrUPZOMBWyE4fryCAQkveIGtab~q1hmKmv4bTwa1Dhps2oStMudd0Ao1AaXmjU6Ln2MtDKFNdNkgTZmKrdTuHndusOqd1UKsNkSW3tQW~2wuYjTlI09RMv8Gxu0793Q5t1nL0LGuIP~23Jn-PmUP8fDRpWzSn8-U90MR~JWxdEm0ObT4UGAkcPE5FqJRVUkyc46Y5w__'

const tmpFriends: TmpFriendType[] = [
  {
    index: 1,
    name: 'John Doe',
    online: true,
    image_url: tmpImage,
  },
  {
    index: 2,
    name: 'Bob Smith',
    online: false,
    image_url: tmpImage,
  },
  {
    index: 3,
    name: 'ㅇㅅㅇ',
    online: true,
    image_url: tmpImage,
  },
  {
    index: 4,
    name: 'John Doe',
    online: true,
    image_url: tmpImage,
  },
  {
    index: 5,
    name: 'Bob Smith',
    online: false,
    image_url: tmpImage,
  },
  {
    index: 6,
    name: 'ㅇㅅㅇ',
    online: true,
    image_url: tmpImage,
  },
]

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
    peerName: peerProfile?.nickname,
    peerProfileImage: peerProfileImage?.result.profileImageUrl ?? defaultImage,
    recentMessage: recentMessage ?? undefined,
  }
}

export default async function layout({
  children,
}: {
  children: React.ReactNode
}) {
  // 1:1 방 목록
  const rooms = await getChatRooms()

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
      <div className="messages-container">
        <SearchBox />

        <OnlineFriends friends={tmpFriends} />

        <MessageContainer>
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
        </MessageContainer>
      </div>

      {children}
    </section>
  )
}
