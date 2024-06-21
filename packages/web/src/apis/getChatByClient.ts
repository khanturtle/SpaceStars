const CHAT_BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/chat`

export interface RecentMessageType {
  lastChatMessage: string
  createdAt: string
  unReadCount: number
}
/** 최근 메시지 조회 */
// TODO: API 수정 후 동기화
export async function getRecentMessage(roomUuid: string, token: string) {
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
    console.log(data)
    return data
  } catch (err) {
    console.error(err)
    return undefined
  }
}
