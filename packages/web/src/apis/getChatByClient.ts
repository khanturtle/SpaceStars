import {
  ChatRoomMemberType,
  RecentMessageType,
  UnreadMessageCount,
} from '@/types/type'

const CHAT_BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/chat`

/** 최근 메시지 조회 */
export async function getRecentMessage(
  roomUuid: string,
  token: string,
): Promise<RecentMessageType | null> {
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
    if (!response.ok) {
      throw new Error('Failed get recentMessage')
    }

    const data = await response.json()
    return data.result
  } catch (err) {
    console.error(err)
    return null
  }
}

/** 1:1 안읽은 메시지 개수 조회 */
export async function getUnreadMessageCount(
  roomUuid: string,
  token: string,
): Promise<UnreadMessageCount | null> {
  try {
    const response = await fetch(
      `${CHAT_BASE_URL}/one-to-one/message/recent/count/${roomUuid}`,
      {
        headers: {
          'Content-Type': 'application/json',
          Authorization: token ? token : '',
        },
      },
    )
    if (!response.ok) {
      throw new Error('Failed to getUnreadMessageCount')
    }
    const data = await response.json()
    return data
  } catch (err) {
    // console.error(err)
    return null
  }
}

/** 최근 메시지 조회 그룹 */
export async function getRecentMessageByTeam(
  roomUuid: string,
  token: string,
): Promise<RecentMessageType | null> {
  try {
    const response = await fetch(
      `${CHAT_BASE_URL}/team/message/recent/${roomUuid}`,
      {
        headers: {
          'Content-Type': 'application/json',
          Authorization: token ? token : '',
        },
        next: { tags: ['recentMessage'] },
      },
    )
    if (!response.ok) {
      throw new Error('Failed get recentMessage')
    }

    const data = await response.json()
    return data.result
  } catch (err) {
    console.error(err)
    return null
  }
}

/** 안읽은 메시지 개수 조회 그룹 */
export async function getUnreadMessageCountByTeam(
  roomUuid: string,
  token: string,
): Promise<UnreadMessageCount | null> {
  try {
    const response = await fetch(
      `${CHAT_BASE_URL}/team/message/recent/count/${roomUuid}`,
      {
        headers: {
          'Content-Type': 'application/json',
          Authorization: token ? token : '',
        },
      },
    )
    if (!response.ok) {
      throw new Error('Failed to getUnreadMessageCount')
    }
    const data = await response.json()
    return data
  } catch (err) {
    // console.error(err)
    return null
  }
}

/** 1:1 방 참여자 조회 */
export async function getRoomMember(
  roomUuid: string,
  _token?: string,
): Promise<ChatRoomMemberType[]> {
  try {
    const response = await fetch(
      `${CHAT_BASE_URL}/one-to-one/chatroom/${roomUuid}`,
      {
        headers: {
          'Content-Type': 'application/json',
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
