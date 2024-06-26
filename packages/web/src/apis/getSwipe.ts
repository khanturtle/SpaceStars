import { getServerSession } from 'next-auth/next'

import { options } from '@/app/api/auth/[...nextauth]/options'

const BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/swipe`

/** 스와이프 목록 조회 */
export async function getSwipeList(pageSize: number, page: number) {
  const session = await getServerSession(options)
  const token = session?.user?.data.accessToken

  try {
    const response = await fetch(
      `${BASE_URL}?pageSize=${pageSize}&page=${page}`,
      {
        headers: {
          'Content-Type': 'application/json',
          Authorization: token ? token : '',
        },
        next: {
          tags: ['swipe'],
        },
      },
    )
    if (!response.ok) {
      throw new Error('Failed to getSwipeList')
    }

    const data = await response.json()
    return data.result
  } catch (error) {
    console.error(error)
    return undefined
  }
}

/** 스와이프 목록 조회 AI */
export async function getSwipeListByAI(pageSize: number, page: number) {
  const session = await getServerSession(options)
  const token = session?.user?.data.accessToken

  try {
    const response = await fetch(
      `${BASE_URL}/ai?pageSize=${pageSize}&page=${page}`,
      {
        headers: {
          'Content-Type': 'application/json',
          Authorization: token ? token : '',
        },
        next: {
          tags: ['swipeAI'],
        },
      },
    )
    if (!response.ok) {
      throw new Error('Failed to getSwipeListByAI')
    }

    const data = await response.json()
    return data.result
  } catch (error) {
    console.error(error)
    return undefined
  }
}
