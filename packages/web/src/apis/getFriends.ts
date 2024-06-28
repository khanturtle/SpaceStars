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
      next: {
        tags: ['friends'],
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
  _query?: string,
): Promise<(ApiResponseType & { result: FriendsListType[] }) | undefined> {
  let token

  if (_token) {
    token = _token
  } else {
    const session = await getServerSession(options)
    token = session?.user?.data.accessToken
  }
  const query = _query ? _query : 'all'
  console.log('query', query)
  try {
    const response = await fetch(`${BASE_URL}/list?type=${query}`, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: token ? token : '',
      },
      next: {
        tags: ['friends'],
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

/** 친구 보낸 요청 목록 */
export async function getFriendsSendList(_token?: string) {
  let token

  if (_token) {
    token = _token
  } else {
    const session = await getServerSession(options)
    token = session?.user?.data.accessToken
  }

  try {
    const response = await fetch(`${BASE_URL}/request?type=send`, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: token ? token : '',
      },
      next: {
        tags: ['friends'],
      },
    })
    if (!response.ok) {
      throw new Error('Failed to getFriendsSendList')
    }

    const data = await response.json()
    return data.result
  } catch (error) {
    console.error(error)
    return
  }
}

/** 친구 받은 요청 목록 */
export async function getFriendsRequestList(_token?: string) {
  let token

  if (_token) {
    token = _token
  } else {
    const session = await getServerSession(options)
    token = session?.user?.data.accessToken
  }

  try {
    const response = await fetch(`${BASE_URL}/request`, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: token ? token : '',
      },
      next: {
        tags: ['friends'],
      },
    })
    if (!response.ok) {
      throw new Error('Failed to getFriendsRequestList')
    }

    const data = await response.json()
    return data.result
  } catch (error) {
    console.error(error)
    return
  }
}
