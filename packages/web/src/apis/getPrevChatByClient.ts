const BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/chat`

/** 안 읽은 메시지 조회 */
export async function getUnreadMessage(roomNumber: string, token: string) {
  try {
    const response = await fetch(
      `${BASE_URL}/one-to-one/message/unread/${roomNumber}`,
      {
        headers: {
          'Content-Type': 'application/json',
          Authorization: token ? token : '',
        },
      },
    )

    if (!response.ok) {
      throw new Error('Failed to getUnreadMessage')
    }

    return await response.json()
  } catch (error) {
    console.error(error)
    return
  }
}
/** 읽은 메시지 조회 */
export async function getReadMessage(roomNumber: string, token: string) {
  try {
    const response = await fetch(
      `${BASE_URL}/one-to-one/message/${roomNumber}`,
      {
        headers: {
          'Content-Type': 'application/json',
          Authorization: token ? token : '',
        },
      },
    )

    if (!response.ok) {
      throw new Error('Failed to getReadMessage')
    }

    return await response.json()
  } catch (error) {
    console.error(error)
    return
  }
}

/** 팀채팅 안읽은 메시지 조회 */
export async function getUnreadMessageByTeam(
  roomNumber: string,
  token: string,
) {
  try {
    const response = await fetch(
      `${BASE_URL}/team/message/unread/${roomNumber}`,
      {
        headers: {
          'Content-Type': 'application/json',
          Authorization: token ? token : '',
        },
      },
    )

    if (!response.ok) {
      throw new Error('Failed to getUnreadMessage')
    }

    return await response.json()
  } catch (error) {
    console.error(error)
    return
  }
}

/** 팀채팅 읽은 메시지 조회 */
export async function getReadMessageByTeam(roomNumber: string, token: string) {
  try {
    const response = await fetch(
      `${BASE_URL}/team/message/${roomNumber}`,
      {
        headers: {
          'Content-Type': 'application/json',
          Authorization: token ? token : '',
        },
      },
    )
    
    if (!response.ok) {
      throw new Error('Failed to getReadMessage')
    }

    return await response.json()
  } catch (error) {
    console.error(error)
    return
  }
}