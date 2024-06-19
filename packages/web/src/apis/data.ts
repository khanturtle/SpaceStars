const BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/data`

export interface MbtiType {
  id: number
  mbtiName: string
}

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
