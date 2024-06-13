const MEMBER_BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/member`

/** 내 정보 받아오기 */
export async function getProfile() {
  try {
    const response = await fetch(`${MEMBER_BASE_URL}`)
    return response
  } catch (error) {
    console.error(error)
    return false
  }
}

/** 프로필 유무 확인하기 */
export async function getIsProfile(token: string) {
  try {
    const response = await fetch(`${MEMBER_BASE_URL}/profile/exist`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        Authorization: token ? token : '',
      },
    })
    return response
  } catch (e) {
    console.error(e)
    return false
  }
}
