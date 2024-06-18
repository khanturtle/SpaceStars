import { getServerSession } from 'next-auth/next'

import { options } from '@/app/api/auth/[...nextauth]/options'

const ProfileImg_BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/profile/image`

export interface ProfileImageType {
  profileImageUrl: string
}

/** 대표 프로필 조회 - 나 */
export async function getMainProfileImg(
  _token?: string,
): Promise<ProfileImageType | undefined> {
  let token

  if (_token) {
    token = _token
  } else {
    const session = await getServerSession(options)
    token = session?.user?.data.accessToken
  }
  if (!token) return undefined

  try {
    const response = await fetch(`${ProfileImg_BASE_URL}/main`, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: token ? token : '',
      },
    })
    const data = await response.json()

    if (data.code !== 200) {
      throw new Error('Failed to get main profileImage')
    }

    return data.result
  } catch (error) {
    console.error(error)
    return undefined
  }
}

/** 대표 프로필 조회 - UUID */
export async function getMainProfileImgByUuid(
  uuid: string,
  _token?: string,
): Promise<ProfileImageType | undefined | null> {
  let token

  if (_token) {
    token = _token
  } else {
    const session = await getServerSession(options)
    token = session?.user?.data.accessToken
  }

  if (!token) return undefined

  try {
    const response = await fetch(`${ProfileImg_BASE_URL}/main/${uuid}`, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: token ? token : '',
      },
    })
    const data = await response.json()
    if (data.code !== 200) {
      throw new Error('Failed to get main profileImage')
    }
    return data.result
  } catch (error) {
    console.error(error)
    return undefined
  }
}

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
    return data.result
  } catch (error) {
    console.error(error)
    return undefined
  }
}
