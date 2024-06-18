const MEMBER_BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/profile`

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
