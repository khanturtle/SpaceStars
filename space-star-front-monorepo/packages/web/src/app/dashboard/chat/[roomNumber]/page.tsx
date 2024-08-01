import { getServerSession } from 'next-auth'

import { options } from '@/app/api/auth/[...nextauth]/options'

import ChatRoomContainer from '@/containers/chat/ChatRoomContainer'

export default async function page({
  params,
}: {
  params: { roomNumber: string }
}) {
  const session = await getServerSession(options)
  const UUID = session?.user?.data.uuid
  const token = session?.user?.data.accessToken

  const roomNumber = params.roomNumber

  return (
    <section className="flex-1">
      <ChatRoomContainer roomNumber={roomNumber} UUID={UUID} token={token} />
    </section>
  )
}
