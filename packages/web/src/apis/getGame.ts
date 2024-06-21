import { GameType, GameTypes } from '@/types/type'

const GAME_BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/game`

/** 전체 게임 조회 */
export async function getGames(): Promise<GameTypes[]> {
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

/** 단일 게임 조회 */
export async function getGameById(gameId: number): Promise<GameType | null> {
  try {
    const response = await fetch(`${GAME_BASE_URL}/${gameId}`)
    const data = await response.json()

    if (data.code !== 200) {
      throw new Error('Failed getGameById')
    }

    return data.result
  } catch (err) {
    console.error(err)
    return null
  }
}

export interface IsOptionType {
  isClass: true
  isPosition: false
  isServer: true
  isTier: false
}
/** 게임 옵션 여부 조회 */
export async function getGameOptions(
  gameId: number,
): Promise<IsOptionType | undefined> {
  try {
    const response = await fetch(`${GAME_BASE_URL}/option/${gameId}`)
    const data = await response.json()

    if (data.code !== 200) {
      throw new Error('Failed get options')
    }

    return data.result
  } catch (err) {
    console.error(err)
    return
  }
}

export interface GameOptionDetailType {
  index: number
  id: number
  image: string
  name: string
  nameKor: string
}
/** 게임 옵션 종류 조회 */
export async function getGameOptionDetail(
  gameId: number,
  option: 'isClass' | 'isPosition' | 'isServer' | 'isTier',
): Promise<GameOptionDetailType[]> {
  const OPTION_NAME = {
    isClass: 'class',
    isPosition: 'position',
    isServer: 'server',
    isTier: 'tier',
  }

  const optionName = OPTION_NAME[option]

  try {
    const response = await fetch(`${GAME_BASE_URL}/${optionName}/${gameId}`)
    const data = await response.json()

    if (data.code !== 200) {
      throw new Error('Failed get option detail')
    }

    return data.result
  } catch (err) {
    console.error(err)
    return []
  }
}
