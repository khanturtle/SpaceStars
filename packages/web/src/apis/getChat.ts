import { getServerSession } from 'next-auth/next'

import { options } from '@/app/api/auth/[...nextauth]/options'
import { ChatRoomMemberType, ChatRoomType } from '@/types/type'

const CHAT_BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/chat`

/** 1:1 방 리스트 조회 */
export async function getChatRooms(): Promise<ChatRoomType[]> {
  const session = await getServerSession(options)
  const token = session?.user?.data.accessToken

  if (!token) return []

  try {
    const response = await fetch(`${CHAT_BASE_URL}/one-to-one/chatroom`, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: token ? token : '',
      },
    })

    if (!response.ok) {
      throw new Error('Failed to getChatRooms')
    }

    const data = await response.json()
    return data.result
  } catch (err) {
    // console.error(err)
    return []
  }
}

/** 1:1 방 참여자 조회 */
export async function getRoomMember(
  roomUuid: string,
): Promise<ChatRoomMemberType[]> {
  const session = await getServerSession(options)
  const token = session?.user?.data.accessToken

  try {
    const response = await fetch(
      `${CHAT_BASE_URL}/one-to-one/chatroom/${roomUuid}`,
      {
        headers: {
          'Content-Type': 'application/json',
          Authorization: token ? token : '',
        },
      },
    )

    if (!response.ok) {
      throw new Error('Failed to getRoomDetail')
    }
    const data = await response.json()
    return data.result
  } catch (err) {
    // console.error(err)
    return []
  }
}
