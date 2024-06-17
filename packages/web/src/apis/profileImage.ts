import { getServerSession } from 'next-auth/next'

import { options } from '@/app/api/auth/[...nextauth]/options'

const ProfileImg_BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/profile/image`

export interface ProfileImageType {
  profileImageUrl: string
}

/** 대표 프로필 조회 */
export async function getMainProfileImg(): Promise<
  ProfileImageType | undefined
> {
  try {
    const response = await fetch(`${ProfileImg_BASE_URL}/main`)
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

/** 대표 프로필 조회 */
export async function getMainProfileImgByUuid(
  uuid: string,
): Promise<ProfileImageType | undefined | null> {
  const session = await getServerSession(options)
  const token = session?.user?.data.accessToken

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
