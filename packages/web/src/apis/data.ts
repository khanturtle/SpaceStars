const BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/data`

export async function getMbtiList() {
  try {
    const response = await fetch(`${BASE_URL}/mbti/list`)
    const data = await response.json()
    console.log(data)
    if (!data) {
      throw new Error('Failed to get mbti')
    }
    return data.result
  } catch (error) {
    console.error(error)
    return false
  }
}
