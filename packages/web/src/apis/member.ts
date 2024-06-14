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
