import { getServerSession } from 'next-auth/next'

import { options } from '@/app/api/auth/[...nextauth]/options'

const CHAT_BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/chat`

/** 1:1 방 생성하기 */
export async function createChat(token: string, userUUID: string) {
  try {
    await fetch(`${CHAT_BASE_URL}/one-to-one/chatroom/create`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        Authorization: token ? token : '',
      },
      body: JSON.stringify({
        receiverUuid: userUUID,
      }),
    })
  } catch (err) {
    // console.error(err)
  }
}

export interface RoomType {
  index: number
  roomNumber: string
  otherMemberUuid: string
}
/** 1:1 방 리스트 조회 */
export async function getChatRooms(): Promise<RoomType[]> {
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
    const data = await response.json()

    if (data.code === 200) {
      return data.result
    }
    throw new Error('Failed get chatRooms')
  } catch (err) {
    // console.error(err)
    return []
  }
}

export interface RoomDetailType {
  index: number
  memberUuid: string
}
/** 1:1 방 참여자 조회 */
export async function getRoomDetail(
  roomUuid: string,
): Promise<RoomDetailType[]> {
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
    const data = await response.json()

    if (data.code === 200) {
      return data.result
    }
    throw new Error('Failed get chatRoom Detail')
  } catch (err) {
    // console.error(err)
    return []
  }
}

export interface RecentMessageType {
  lastChatMessage: string
  createdAt: string
  unReadCount: number
}
/** 최근 메시지 조회 */
export async function getRecentMessage(
  roomUuid: string,
): Promise<RecentMessageType | undefined> {
  const session = await getServerSession(options)
  const token = session?.user?.data.accessToken
  if (!token) return undefined
  try {
    const response = await fetch(
      `${CHAT_BASE_URL}/one-to-one/message/recent/${roomUuid}`,
      {
        headers: {
          'Content-Type': 'application/json',
          Authorization: token ? token : '',
        },
        next: { tags: ['recentMessage'] },
      },
    )
    const data = await response.json()

    if (data.code === 200) {
      return data.result
    }
    throw new Error('Failed get recentMessage')
  } catch (err) {
    console.error(err)
    return undefined
  }
}
