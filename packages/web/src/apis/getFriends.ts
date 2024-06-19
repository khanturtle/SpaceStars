import { getServerSession } from 'next-auth/next'

import { options } from '@/app/api/auth/[...nextauth]/options'
import { ApiResponseType } from '@/types/type'

const BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/friend`

export interface IsFriendType extends ApiResponseType {
  result: {
    isFriend: boolean
  }
}

/** 친구 여부 조회 */
export async function getIsFriend(
  uuid: string,
  _token?: string,
): Promise<IsFriendType | undefined> {
  let token

  if (_token) {
    token = _token
  } else {
    const session = await getServerSession(options)
    token = session?.user?.data.accessToken
  }
  if (!token) return undefined

  try {
    const response = await fetch(`${BASE_URL}/is-friend/${uuid}`, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: token ? token : '',
      },
    })
    const data = await response.json()

    if (!data) {
      throw new Error('Failed to getIsFriend')
    }

    return data
  } catch (error) {
    console.error(error)
    return undefined
  }
}

// FIXME: 나중에 삭제
export interface TmpFriendType {
  index: number
  name: string
  online: boolean
  image_url: string
}
