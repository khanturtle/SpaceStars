import { getServerSession } from 'next-auth'

import { options } from '@/app/api/auth/[...nextauth]/options'

import ChatRoomContainer from '@/containers/chat/ChatRoomContainer'
import { getChatroomData } from '@/lib/getChatroomData'

export default async function page({
  params,
}: {
  params: { roomNumber: string }
}) {
  const session = await getServerSession(options)
  const UUID = session?.user?.data.uuid

  const roomNumber = params.roomNumber
  const roomMemberData = await getChatroomData(roomNumber)

  return (
    <section className="flex-1">
      <ChatRoomContainer
        roomNumber={roomNumber}
        roomMemberData={roomMemberData}
        UUID={UUID}
      />
    </section>
  )
}
