const MEMBER_BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/profile`

// TODO: Promise Type 지정하기

/** 프로필 생성하기 */
export async function createNewProfile(token: string) {
  try {
    await fetch(`${MEMBER_BASE_URL}/exist`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        Authorization: token ? token : '',
      },
    })
  } catch (e) {
    console.error(e)
  }
}

/** 대표게임 유무 확인하기 */
// FIXME: API 생성되면 확인
export async function getMainGame(token: string) {
  try {
    const response = await fetch(`${MEMBER_BASE_URL}/main-game`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        Authorization: token ? token : '',
      },
    })
    if (response.status === 404) {
      throw new Error('서버 확인')
    }
    console.log(response)
    return await response.json()
  } catch (e) {
    console.error(e)
    return false
  }
}

/** 프로필 정보 조회 */
export async function getProfileInfo(token: string) {
  try {
    const response = await fetch(`${MEMBER_BASE_URL}/info`, {
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

/** 내가 하는 게임 조회 */
export async function getPlayGame(token: string) {
  try {
    const response = await fetch(`${MEMBER_BASE_URL}/play-game`, {
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
