import {
  ApiResponseType,
  MainGameType,
  PlayGameType,
  ProfileInfoType,
} from '@/types/type'

const MEMBER_BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/profile`

// TODO: Promise Type 지정하기

/** 로그인 시, 대표게임 유무 확인하기 */
export async function getMainGame(
  token: string,
): Promise<(ApiResponseType & { result: MainGameType }) | undefined> {
  try {
    const response = await fetch(`${MEMBER_BASE_URL}/main-game`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        Authorization: token ? token : '',
      },
    })
    if (!response.ok) {
      throw new Error('Failed to getMainGame')
    }

    return await response.json()
  } catch (e) {
    console.error(e)
    return undefined
  }
}

/** 프로필 정보 조회 - 나 */
export async function getProfileInfo(
  token: string,
): Promise<(ApiResponseType & ProfileInfoType) | undefined> {
  try {
    const response = await fetch(`${MEMBER_BASE_URL}/info`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        Authorization: token ? token : '',
      },
    })
    if (!response.ok) {
      throw new Error('Failed to getProfileInfo')
    }
    return await response.json()
  } catch (e) {
    console.error(e)
    return undefined
  }
}

/** 내가 하는 게임 조회 - 나 */
export async function getPlayGame(
  token: string,
): Promise<(ApiResponseType & { result: PlayGameType[] }) | undefined> {
  try {
    const response = await fetch(`${MEMBER_BASE_URL}/play-game`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        Authorization: token ? token : '',
      },
    })
    if (!response.ok) {
      throw new Error('Failed to getPlayGame')
    }
    return await response.json()
  } catch (e) {
    console.error(e)
    return undefined
  }
}

/** 좋아하는 게임 조회 */
export async function getLikedGame(token: string) {
  try {
    const response = await fetch(`${MEMBER_BASE_URL}/liked-game`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        Authorization: token ? token : '',
      },
    })
    const data = await response.json()
    if (data.code === 200) {
      return data.result
    }
  } catch (e) {
    console.error(e)
    return false
  }
}
