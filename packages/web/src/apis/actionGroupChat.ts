'use server'

import { getServerSession } from 'next-auth/next'

import { options } from '@/app/api/auth/[...nextauth]/options'

const BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/chat`

/** 채팅방 나가기 */
export async function exitChatRoom(roomNumber: string) {
  const session = await getServerSession(options)
  const token = session?.user?.data.accessToken

  try {
    const response = await fetch(
      `${BASE_URL}/team/chatroom/exit/${roomNumber}`,
      {
        method: 'PATCH',
        headers: {
          'Content-Type': 'application/json',
          Authorization: token,
        },
      },
    )

    if (!response.ok) {
      throw new Error('Failed exitRoom')
    }

    const data = await response.json()
    // console.log(data)

    return data
  } catch (err) {
    console.error(err)
  }
}

/** 큐 입장 */
export async function enteredQueue(gameName: string) {
  const session = await getServerSession(options)
  const token = session?.user?.data.accessToken

  const bodyData = {
    gameName: gameName,
  }
  try {
    const response = await fetch(`${BASE_URL}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        Authorization: token,
      },
      body: JSON.stringify(bodyData),
    })

    if (!response.ok) {
      throw new Error('Failed enteredQueue')
    }

    const data = await response.json()
    // console.log(data)

    return data
  } catch (err) {
    console.error(err)
  }
}
