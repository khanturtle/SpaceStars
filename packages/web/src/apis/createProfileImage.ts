'use server'

import { getServerSession } from 'next-auth/next'

import { options } from '@/app/api/auth/[...nextauth]/options'
import { revalidateTag } from 'next/cache'

const ProfileImg_BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/profile/image`

type ReqType = {
  profileImageUrl: string
  main: boolean
}
/** 프로필 추가 */
export async function createProfileImage(req: ReqType, _token?: string) {
  let token

  if (_token) {
    token = _token
  } else {
    const session = await getServerSession(options)
    token = session?.user?.data.accessToken
  }

  if (!token) return undefined

  try {
    const response = await fetch(`${ProfileImg_BASE_URL}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        Authorization: token ? token : '',
      },
      body: JSON.stringify(req),
    })
    const data = await response.json()
    if (data.code !== 200) {
      throw new Error('Failed to created profileImage')
    }

    await revalidateTag('profileImages')

    return data
  } catch (error) {
    console.error(error)
    return undefined
  }
}
