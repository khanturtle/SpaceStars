import { getServerSession } from 'next-auth/next'

import { options } from '@/app/api/auth/[...nextauth]/options'
import { ApiResponseType, FriendsListType, FriendType } from '@/types/type'

const BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/friend`

/** 회원 상호 상태 조회 */
export async function getFriendType(
  uuid: string,
  _token?: string,
): Promise<(ApiResponseType & { result: FriendType }) | undefined> {
  let token

  if (_token) {
    token = _token
  } else {
    const session = await getServerSession(options)
    token = session?.user?.data.accessToken
  }

  try {
    const response = await fetch(`${BASE_URL}/is-friend/request/${uuid}`, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: token ? token : '',
      },
    })

    if (!response.ok) {
      throw new Error('Failed to getIsFriend')
    }

    const data = await response.json()
    return data
  } catch (error) {
    console.error(error)
    return
  }
}

/** 친구 리스트 조회 */
export async function getFriendsList(
  _token?: string,
): Promise<(ApiResponseType & { result: FriendsListType[] }) | undefined> {
  let token

  if (_token) {
    token = _token
  } else {
    const session = await getServerSession(options)
    token = session?.user?.data.accessToken
  }

  try {
    const response = await fetch(`${BASE_URL}/list`, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: token ? token : '',
      },
    })
    if (!response.ok) {
      throw new Error('Failed to getFriendsList')
    }

    const data = await response.json()
    return data
  } catch (error) {
    console.error(error)
    return
  }
}

// FIXME: 나중에 삭제
export interface TmpFriendType {
  index: number
  name: string
  online: boolean
  image_url: string
}
