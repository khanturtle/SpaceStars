'use server'

import { getServerSession } from 'next-auth/next'

import { options } from '@/app/api/auth/[...nextauth]/options'

const BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/swipe`

/** 스와이프 요청 보내기 */
export async function postSwipe(
  prevState: {
    code: number
    message: string
    result: null
  },
  formData: FormData,
) {
  const session = await getServerSession(options)
  const token = session?.user?.data.accessToken

  const postFormData = {
    matchToMember: formData.get('matchToMember'),
    memo: formData.get('memo'),
  }

  // memo가 없으면, return
  if (formData.get('memo') === '') {
    return {
      code: 0,
      message: '메시지를 입력해주세요.',
      result: null,
    }
  }

  try {
    const response = await fetch(`${BASE_URL}/add`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        Authorization: token ? token : '',
      },
      body: JSON.stringify(postFormData),
    })

    if (!response.ok) {
      throw new Error('Failed to postSwipe')
    }

    const data = await response.json()

    return { ...data }
  } catch (error) {
    console.error(error)
    return {
      code: 0,
      message: '다시 시도해주세요',
      result: null,
    }
  }
}
