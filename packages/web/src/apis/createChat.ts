'use server'

import { getServerSession } from 'next-auth/next'

import { options } from '@/app/api/auth/[...nextauth]/options'

const BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/chat`

/** 1:1 채팅방 생성하기 */
export async function createOnetoOneChat(uuid: string) {
  const session = await getServerSession(options)
  const token = session?.user?.data.accessToken

  try {
    const response = await fetch(`${BASE_URL}/one-to-one/chatroom/create`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        Authorization: token,
      },
      body: JSON.stringify({
        receiverUuid: uuid,
      }),
    })

    if (!response.ok) {
      // 채팅방 생성 실패
      throw new Error('Failed to createOnetoOneChat')
    }

    // 채팅방 생성 성공
    return await response.json()
  } catch (error) {
    console.error(error)
  }
}
