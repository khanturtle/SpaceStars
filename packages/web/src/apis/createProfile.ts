const BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/profile`

/** 프로필 생성하기 */
export async function createNewProfile(token: string) {
  try {
    await fetch(`${BASE_URL}/exist`, {
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
