import { getServerSession } from 'next-auth/next'

import { options } from '@/app/api/auth/[...nextauth]/options'
import { ApiResponseType, ProfileType } from '@/types/type'

const MEMBER_BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/member`

/** 내 정보 받아오기 */
export async function getAuthProfile(
  _token?: string,
): Promise<(ApiResponseType & { result: ProfileType }) | undefined> {
  let token = ''

  if (_token) {
    token = _token
  } else {
    const session = await getServerSession(options)
    token = session?.user?.data.accessToken
  }

  try {
    const response = await fetch(`${MEMBER_BASE_URL}/info`, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: token ? token : '',
      },
    })

    if (!response.ok) {
      throw new Error('Failed to getAuthProfile')
    }

    return await response.json()
  } catch (error) {
    console.error(error)
    return
  }
}

/** 상대방 정보 받아오기 */
export async function getProfileByUuid(
  uuid: string,
  _token?: string,
): Promise<(ApiResponseType & { result: ProfileType }) | undefined> {
  let token = ''

  if (_token) {
    token = _token
  } else {
    const session = await getServerSession(options)
    token = session?.user?.data.accessToken
  }

  try {
    const response = await fetch(`${MEMBER_BASE_URL}/info/${uuid}`, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: token ? token : '',
      },
    })
    if (!response.ok) {
      throw new Error('Failed to getProfileByUuid')
    }

    return await response.json()
  } catch (error) {
    console.error(error)
    return
  }
}
