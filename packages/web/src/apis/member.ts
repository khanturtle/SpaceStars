import { getServerSession } from 'next-auth/next'

import { options } from '@/app/api/auth/[...nextauth]/options'

const BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/member`

/** 닉네임으로 회원 찾기 */
export async function getUserByNickname(
  targetNickname: string,
  _token?: string,
) {
  let token: string | undefined

  if (_token) {
    token = _token
  } else {
    const session = await getServerSession(options)
    token = session?.user?.data.accessToken
  }

  if (!token) return undefined

  try {
    const response = await fetch(`${BASE_URL}/uuid/${targetNickname}`, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: token ? token : '',
      },
    })
    const data = await response.json()

    if (!data) {
      throw new Error('Failed to get user by nickname')
    }
    return data
  } catch (error) {
    console.error(error)
    return
  }
}
