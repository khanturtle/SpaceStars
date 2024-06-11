const GAME_BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/game`

// FIXME: GAME API 수정 요청
export interface GameType {
  index: number
  gameId: number
  gameName: string
  gameNameKor: string
  gameImage: string
}

export async function getGames(): Promise<GameType[]> {
  try {
    const response = await fetch(`${GAME_BASE_URL}`)
    const data = await response.json()
    if (!data.ok) {
      throw new Error('Failed get games')
    }
    return data.result
  } catch (err) {
    console.error(err)
    return []
  }
}
