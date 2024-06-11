const GAME_BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/game`

export interface GameType {
  index: number
  gameId: number
  gameNameKor: string
  gameName: string
  gameImage: string
  gameLogoImage: string
}

export async function getGames(): Promise<GameType[]> {
  try {
    const response = await fetch(`${GAME_BASE_URL}`)
    const data = await response.json()

    if (data.code !== 200) {
      throw new Error('Failed get games')
    }

    return data.result
  } catch (err) {
    console.error(err)
    return []
  }
}
