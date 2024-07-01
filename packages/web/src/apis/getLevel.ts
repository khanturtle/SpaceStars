import { getServerSession } from 'next-auth'

import { options } from '@/app/api/auth/[...nextauth]/options'

/** 레벨 조회 */
export async function getLevel() {
  const session = await getServerSession(options)
  const { accessToken } = session?.user?.data || {}

  try {
    const response = await fetch(`${process.env.NEXT_PUBLIC_API_URL_V1}/rate`, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: accessToken,
      },
    })

    if (!response.ok) {
      throw new Error('Failed to getLevel')
    }

    const data = await response.json()
    return data.result
  } catch (error) {
    console.error(error)
    return
  }
}

export async function getExp() {
  const session = await getServerSession(options)
  const { accessToken } = session?.user?.data || {}

  try {
    const response = await fetch(
      `${process.env.NEXT_PUBLIC_API_URL_V1}/rate/exp`,
      {
        headers: {
          'Content-Type': 'application/json',
          Authorization: accessToken,
        },
      },
    )

    if (!response.ok) {
      throw new Error('Failed to getExp')
    }

    const data = await response.json()
    return data.result
  } catch (error) {
    console.error(error)
    return
  }
}

export async function getLevelInfo(level: number) {
  const session = await getServerSession(options)
  const { accessToken } = session?.user?.data || {}

  try {
    const response = await fetch(
      `${process.env.NEXT_PUBLIC_API_URL_V1}/rate/info?level=${level}`,
      {
        headers: {
          'Content-Type': 'application/json',
          Authorization: accessToken,
        },
      },
    )

    if (!response.ok) {
      throw new Error('Failed to getLevelInfo')
    }

    const data = await response.json()
    return data.result
  } catch (error) {
    console.error(error)
    return
  }
}
