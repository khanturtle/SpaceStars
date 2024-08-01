import { getServerSession } from 'next-auth/next'

import { options } from '@/app/api/auth/[...nextauth]/options'
import {
  ApiResponseType,
  ProfileImagesType,
  ProfileImageType,
} from '@/types/type'

const BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/profile/image`

/** 대표 프로필 조회 - 나 */
export async function getMainProfileImage(
  _token?: string,
): Promise<(ApiResponseType & { result: ProfileImageType }) | undefined> {
  let token = ''

  if (_token) {
    token = _token
  } else {
    const session = await getServerSession(options)
    token = session?.user?.data.accessToken
  }

  try {
    const response = await fetch(`${BASE_URL}/main`, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: token ? token : '',
      },
      next: {
        tags: ['profileImage'],
      },
    })

    const data = await response.json()

    if (!response.ok) {
      throw new Error('Failed to getMainProfileImage')
    }
    return data
  } catch (error) {
    console.error(error)
    return undefined
  }
}

/** 프로필 리스트 조회 - 나 */
export async function getProfileImages(
  _token?: string,
): Promise<(ApiResponseType & { result: ProfileImagesType[] }) | undefined> {
  let token = ''

  if (_token) {
    token = _token
  } else {
    const session = await getServerSession(options)
    token = session?.user?.data.accessToken
  }

  try {
    const response = await fetch(`${BASE_URL}`, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: token ? token : '',
      },
      next: {
        tags: ['profileImages'],
      },
    })
    if (!response.ok) {
      throw new Error('Failed to getProfileImages')
    }
    return await response.json()
  } catch (error) {
    console.error(error)
    return undefined
  }
}

/** 대표 프로필 조회 - UUID */
export async function getMainProfileImageByUuid(
  uuid: string,
  _token?: string,
): Promise<(ApiResponseType & { result: ProfileImageType }) | undefined> {
  let token

  if (_token) {
    token = _token
  } else {
    const session = await getServerSession(options)
    token = session?.user?.data.accessToken
  }

  try {
    const response = await fetch(`${BASE_URL}/main/${uuid}`, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: token ? token : '',
      },
    })
    if (!response.ok) {
      throw new Error('Failed to getMainProfileImageByUuid')
    }
    return await response.json()
  } catch (error) {
    console.error(error)
    return undefined
  }
}

/** 프로필 리스트 조회 - UUID */
export async function getProfileImagesByUuid(
  uuid: string,
  _token?: string,
): Promise<(ApiResponseType & { result: ProfileImagesType[] }) | undefined> {
  let token

  if (_token) {
    token = _token
  } else {
    const session = await getServerSession(options)
    token = session?.user?.data.accessToken
  }

  try {
    const response = await fetch(`${BASE_URL}/${uuid}`, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: token ? token : '',
      },
    })
    if (!response.ok) {
      throw new Error('Failed to getProfileImagesByUuid')
    }
    return await response.json()
  } catch (error) {
    console.error(error)
    return
  }
}
