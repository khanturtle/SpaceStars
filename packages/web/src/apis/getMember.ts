import { getServerSession } from 'next-auth/next'

import { options } from '@/app/api/auth/[...nextauth]/options'
import { ApiResponseType } from '@/types/type'

const BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/member`

/** 닉네임으로 회원 찾기 */
export async function getUuidByNickname(
  targetNickname: string,
  _token?: string,
): Promise<
  (ApiResponseType & { result: { uuid: string | null } }) | undefined
> {
  let token = ''

  if (_token) {
    token = _token
  } else {
    const session = await getServerSession(options)
    token = session?.user?.data.accessToken
  }

  try {
    const response = await fetch(`${BASE_URL}/uuid/${targetNickname}`, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: token ? token : '',
      },
    })

    if (!response.ok) {
      throw new Error('Failed to getUuidByNickname')
    }

    return await response.json()
  } catch (error) {
    console.error(error)
    return
  }
}
