const MEMBER_BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/profile`

/** 프로필 유무 확인하기 */
export async function getIsProfile(token: string) {
  try {
    const response = await fetch(`${MEMBER_BASE_URL}/exist`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        Authorization: token ? token : '',
      },
    })
    return await response.json()
  } catch (e) {
    console.error(e)
    return false
  }
}
