const BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/chat`

/** 채팅방 정보 조회 */
export async function getGroupChatInfo(roomNumber: string, token: string) {
  try {
    const response = await fetch(`${BASE_URL}/team/chatroom/${roomNumber}`, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: token ? token : '',
      },
    })
    if (!response.ok) {
      throw new Error('Failed get getGroupChatInfo')
    }

    const data = await response.json()
    return data.result
  } catch (err) {
    console.error(err)
    return null
  }
}

/** 그룹 채팅방 멤버 */
export async function getTeamChatRoomsMember(
  roomNumber: string,
  token: string,
) {
  try {
    const response = await fetch(
      `${BASE_URL}/team/chatroom/${roomNumber}/members`,
      {
        headers: {
          'Content-Type': 'application/json',
          Authorization: token ? token : '',
        },
      },
    )

    if (!response.ok) {
      throw new Error('Failed to getTeamChatRoomsMember')
    }

    const data = await response.json()
    return data.result
  } catch (err) {
    // console.error(err)
    return []
  }
}
