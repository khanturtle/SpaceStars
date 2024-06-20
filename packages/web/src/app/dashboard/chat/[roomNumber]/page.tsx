import { getProfileByUuid } from '@/apis/getAuth'
import { getRoomDetail } from '@/apis/chat'
import { getMainProfileImage } from '@/apis/getProfileImage'
import { options } from '@/app/api/auth/[...nextauth]/options'
import ChatRoomContainer from '@/containers/chat/ChatRoomContainer'
import { getServerSession } from 'next-auth'
import { revalidateTag } from 'next/cache'

// async function getRoomData(roomNumber: string) {
//   const session = await getServerSession(options)
//   const UUID = session?.user?.data.uuid

//   // 채팅 참여자
//   const members = await getRoomDetail(roomNumber)
//   console.log(members)
//   const memberUuid = members.find((m) => m.memberUuid === UUID)?.memberUuid
// }

export default async function page({
  params,
}: {
  params: { roomNumber: string }
}) {
  // FIXME: 안됨
  // revalidateTag('recentMessage')
  const session = await getServerSession(options)
  const UUID = session?.user?.data.uuid

  const roomNumber = params.roomNumber
  // const roomData = await getRoomData(roomNumber)

  return (
    <section className="flex-1">
      <ChatRoomContainer roomNumber={roomNumber} UUID={UUID} />
    </section>
  )
}
