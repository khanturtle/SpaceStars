const GAME_BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/game`

export interface GameType {
  index: number
  gameId: number
  gameNameKor: string
  gameName: string
  gameImage: string
  gameLogoImage: string
}

/** 전체 게임 조회 */
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

export interface IsOptionType {
  isClass: true
  isPosition: false
  isServer: true
  isTier: false
}
/** 게임 옵션 조회 */
export async function getGameOptions(gameId: number): Promise<IsOptionType | undefined> {
  try {
    const response = await fetch(`${GAME_BASE_URL}/option/${gameId}`)
    const data = await response.json()

    if (data.code !== 200) {
      throw new Error('Failed get games')
    }

    return data.result
  } catch (err) {
    console.error(err)
    return 
  }
}
