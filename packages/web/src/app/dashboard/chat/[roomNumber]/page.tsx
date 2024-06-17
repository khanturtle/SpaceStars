import { getProfileByUuid } from '@/apis/auth-member'
import { getRoomDetail } from '@/apis/chat'
import { getMainProfileImg } from '@/apis/profileImage'
import { options } from '@/app/api/auth/[...nextauth]/options'
import ChatRoomContainer from '@/containers/chat/ChatRoomContainer'
import { getServerSession } from 'next-auth'

async function getRoomData(roomNumber: string) {
  const session = await getServerSession(options)
  const UUID = session?.user?.data.uuid

  // 채팅 참여자
  const members = await getRoomDetail(roomNumber)
  const memberUuid = members.find((m) => m.memberUuid === UUID)?.memberUuid
}

export default async function page({
  params,
}: {
  params: { roomNumber: string }
}) {
  const session = await getServerSession(options)
  const UUID = session?.user?.data.uuid

  const roomNumber = params.roomNumber

  return (
    <section className="flex-1">
      <ChatRoomContainer roomNumber={roomNumber} UUID={UUID} />
    </section>
  )
}
