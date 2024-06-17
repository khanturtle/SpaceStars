import { getServerSession } from 'next-auth/next'

import { options } from '@/app/api/auth/[...nextauth]/options'

const MEMBER_BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/member`

export interface ProfileType {
  email: string
  nickname: string
  birth: string
  gender: string
  infoAgree: boolean
  createdAt: string
  updatedAt: string
}

/** 내 정보 받아오기 */
export async function getProfile(): Promise<ProfileType | undefined> {
  try {
    const response = await fetch(`${MEMBER_BASE_URL}/info`)
    const data = await response.json()
    if (data.code === 200) {
      return data.result
    }
  } catch (error) {
    console.error(error)
    return undefined
  }
}

/** 상대방 정보 받아오기 */
export async function getProfileByUuid(
  uuid: string,
  _token?: string,
): Promise<ProfileType | undefined> {
  let token

  if (_token) {
    token = _token
  } else {
    const session = await getServerSession(options)
    token = session?.user?.data.accessToken
  }

  try {
    const response = await fetch(`${MEMBER_BASE_URL}/info/${uuid}`, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: token ? token : '',
      },
    })
    const data = await response.json()
    if (data.code === 200) {
      return data.result
    }
  } catch (error) {
    console.error(error)
    return undefined
  }
}
