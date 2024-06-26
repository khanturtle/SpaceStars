const BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/data`

export interface MbtiType {
  id: number
  mbtiName: string
}

/** MBTI 리스트 조회 */
export async function getMbtiList(): Promise<MbtiType[]> {
  try {
    const response = await fetch(`${BASE_URL}/mbti/list`)
    const data = await response.json()

    if (!data) {
      throw new Error('Failed to get mbti')
    }
    return data.result
  } catch (error) {
    console.error(error)
    return []
  }
}

export interface MbtiNameType {
  mbtiName: string
}
/** MBTI id로 이름 조회 */
export async function getMbtiById(id: number): Promise<MbtiNameType | null> {
  try {
    const response = await fetch(`${BASE_URL}/mbti/${id}`)
    const data = await response.json()

    if (!data) {
      throw new Error('Failed to getMbtiById')
    }
    return data.result
  } catch (error) {
    console.error(error)
    return null
  }
}
