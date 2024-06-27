import { getServerSession } from 'next-auth/next'

import { options } from '@/app/api/auth/[...nextauth]/options'

import {
  ApiResponseType,
  LikedGameIdType,
  PlayGameType,
  ProfileInfoType,
} from '@/types/type'

const BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/profile`

/** 프로필 정보 조회 - uuid */
export async function getProfileInfoByUuid(
  uuid: string,
  _token?: string,
): Promise<(ApiResponseType & { result: ProfileInfoType }) | undefined> {
  let token = ''

  if (_token) {
    token = _token
  } else {
    const session = await getServerSession(options)
    token = session?.user?.data.accessToken
  }

  try {
    const response = await fetch(`${BASE_URL}/info/${uuid}`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        Authorization: token ? token : '',
      },
    })
    if (!response.ok) {
      throw new Error('Failed to getProfileInfoByUuid')
    }
    return await response.json()
  } catch (e) {
    console.error(e)
    return
  }
}

/** 내가 하는 게임 조회 - uuid */
export async function getPlayGameByUuid(
  uuid: string,
  _token?: string,
): Promise<(ApiResponseType & { result: PlayGameType[] }) | undefined> {
  let token = ''

  if (_token) {
    token = _token
  } else {
    const session = await getServerSession(options)
    token = session?.user?.data.accessToken
  }

  try {
    const response = await fetch(`${BASE_URL}/play-game/${uuid}`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        Authorization: token ? token : '',
      },
    })
    if (!response.ok) {
      throw new Error('Failed to getPlayGameByUuid')
    }
    return await response.json()
  } catch (e) {
    console.error(e)
    return
  }
}

/** 좋아하는 게임 조회 - uuid */
export async function getLikedGameByUuid(
  uuid: string,
  _token?: string,
): Promise<(ApiResponseType & { result: LikedGameIdType }) | undefined> {
  let token = ''

  if (_token) {
    token = _token
  } else {
    const session = await getServerSession(options)
    token = session?.user?.data.accessToken
  }

  try {
    const response = await fetch(`${BASE_URL}/liked-game/${uuid}`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        Authorization: token ? token : '',
      },
    })
    if (!response.ok) {
      throw new Error('Failed to getLikedGameByUuid')
    }
    return await response.json()
  } catch (e) {
    console.error(e)
    return
  }
}
