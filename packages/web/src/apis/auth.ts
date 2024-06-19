const AUTH_BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/auth`

/** 닉네임 중복검사 */
export async function checkNickname(nickname: string) {
  try {
    const response = await fetch(`${AUTH_BASE_URL}/nickname/${nickname}`)
    const data = await response.json()

    if (!data) {
      throw new Error('Failed to check nickname')
    }
    return data.result
  } catch (error) {
    console.error(error)
    return false
  }
}
