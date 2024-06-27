'use server'

import { getServerSession } from 'next-auth/next'

import { options } from '@/app/api/auth/[...nextauth]/options'
import { join } from 'path'

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

/** 그룹 채팅방 생성하기 */
export async function createTeam(prevState: unknown, formData: FormData) {
  const session = await getServerSession(options)
  const token = session?.user?.data.accessToken

  const postFormData = {
    roomName: formData.get('roomName'),
    gameId: formData.get('gameId'),
    maxMembers: formData.get('maxMembers'),
    isPassword: formData.get('isPassword') === 'on',
    password:
      formData.get('isPassword') === 'on' ? formData.get('password') : null,
    memo: formData.get('memo'),
  }

  try {
    const res = await fetch(`${BASE_URL}/team/chatroom/create`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        Authorization: token,
      },
      body: JSON.stringify(postFormData),
    }).then((r) => r.json())

    console.log(res)

    return { ...res }
  } catch (error) {
    console.error('createTeam:', error)
    return {
      code: -1,
      message: '채팅방 생성에 실패했습니다. 다시 시도해주세요.',
      result: null,
    }
  }
}

/** 그룹 채팅방 입장하기 */
export async function joinTeamForm(prevState: unknown, formData: FormData) {
  const session = await getServerSession(options)
  const token = session?.user?.data.accessToken

  const roomNumber = formData.get('roomNumber')
  const postFormData = {
    isPassword: formData.get('isPassword'),
    password: formData.get('password'),
  }

  try {
    const res = await fetch(`${BASE_URL}/team/chatroom/join/${roomNumber}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        Authorization: token,
      },
      body: JSON.stringify(postFormData),
    }).then((r) => r.json())

    console.log(res)
    return { ...res }
  } catch (error) {
    console.error('joinTeam:', error)
  }
}

export async function joinTeam(roomNumber: string) {
  const session = await getServerSession(options)
  const token = session?.user?.data.accessToken

  try {
    const res = await fetch(`${BASE_URL}/team/chatroom/join/${roomNumber}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        Authorization: token,
      },
      body: JSON.stringify({
        isPassword: false,
      }),
    }).then((r) => r.json())

    return { ...res }
  } catch (error) {
    console.error('joinTeam:', error)
  }
}
