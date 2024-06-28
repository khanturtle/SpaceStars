import { getServerSession } from 'next-auth/next'

import { options } from '@/app/api/auth/[...nextauth]/options'
import { revalidateTag } from 'next/cache'

const BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/friend`

/** 친구 요청 */
export async function sendFriendRequest(uuid: string) {
  const session = await getServerSession(options)
  const token = session?.user?.data.accessToken

  try {
    const response = await fetch(`${BASE_URL}/request`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        Authorization: token ? token : '',
      },
      body: JSON.stringify({
        friendUuid: uuid,
      }),
    })

    if (!response.ok) {
      throw new Error('Failed to sendFriendRequest')
    }

    revalidateTag('friends')
    const data = await response.json()
    return data
  } catch (error) {
    console.error(error)
    return
  }
}

/** 친구 요청 거절 */
export async function rejectFriendRequest(uuid: string) {
  const session = await getServerSession(options)
  const token = session?.user?.data.accessToken

  try {
    const response = await fetch(`${BASE_URL}/request`, {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json',
        Authorization: token ? token : '',
      },
      body: JSON.stringify({
        friendUuid: uuid,
      }),
    })

    if (!response.ok) {
      throw new Error('Failed to rejectFriendRequest')
    }

    revalidateTag('friends')
    const data = await response.json()
    return data
  } catch (error) {
    console.error(error)
    return
  }
}

/** 친구 요청 수락 */
export async function acceptFriendRequest(uuid: string) {
  const session = await getServerSession(options)
  const token = session?.user?.data.accessToken

  try {
    const response = await fetch(`${BASE_URL}/request`, {
      method: 'PATCH',
      headers: {
        'Content-Type': 'application/json',
        Authorization: token ? token : '',
      },
      body: JSON.stringify({
        friendUuid: uuid,
      }),
    })

    if (!response.ok) {
      throw new Error('Failed to acceptFriendRequest')
    }

    revalidateTag('friends')
    const data = await response.json()
    return data
  } catch (error) {
    console.error(error)
    return
  }
}

/** 친구 삭제 */
export async function deleteFriendRequest(uuid: string) {
  const session = await getServerSession(options)
  const token = session?.user?.data.accessToken

  try {
    const response = await fetch(`${BASE_URL}`, {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json',
        Authorization: token ? token : '',
      },
      body: JSON.stringify({
        friendUuid: uuid,
      }),
    })

    if (!response.ok) {
      throw new Error('Failed to deleteFriendRequest')
    }

    revalidateTag('friends')
    const data = await response.json()
    return data
  } catch (error) {
    console.error(error)
    return
  }
}
