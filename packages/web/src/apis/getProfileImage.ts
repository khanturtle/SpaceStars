import { getServerSession } from 'next-auth/next'

import { options } from '@/app/api/auth/[...nextauth]/options'

const ProfileImg_BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/profile/image`

export interface ProfileImageType {
  profileImageUrl: string | null
}
export interface ProfileImagesType extends ProfileImageType {
  index: number
  mainImage: boolean
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

    if (!data) {
      throw new Error('Failed to get main profileImage')
    }

    return data.result
  } catch (error) {
    console.error(error)
    return undefined
  }
}
/** 프로필 리스트 조회 - 나 */
export async function getProfileImages(
  _token?: string,
): Promise<ProfileImagesType[]> {
  let token

  if (_token) {
    token = _token
  } else {
    const session = await getServerSession(options)
    token = session?.user?.data.accessToken
  }

  try {
    const response = await fetch(`${ProfileImg_BASE_URL}`, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: token ? token : '',
      },
      next: {
        tags: ['profileImages'],
      },
    })
    const data = await response.json()

    if (data.code !== 200) {
      throw new Error('Failed to get profileImages')
    }

    return data.result
  } catch (error) {
    console.error(error)
    return []
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
    if (!data) {
      throw new Error('Failed to get main profileImage')
    }
    return data.result
  } catch (error) {
    console.error(error)
    return undefined
  }
}
/** 프로필 리스트 조회 - UUID */
export async function getProfileImagesByUuid(
  uuid: string,
  _token?: string,
): Promise<ProfileImagesType[]> {
  let token

  if (_token) {
    token = _token
  } else {
    const session = await getServerSession(options)
    token = session?.user?.data.accessToken
  }

  try {
    const response = await fetch(`${ProfileImg_BASE_URL}/${uuid}`, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: token ? token : '',
      },
      next: {
        tags: ['profileImages'],
      },
    })
    const data = await response.json()

    if (data.code !== 200) {
      throw new Error('Failed to get profileImages')
    }

    return data.result
  } catch (error) {
    console.error(error)
    return []
  }
}
