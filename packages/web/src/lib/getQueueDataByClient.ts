import { defaultImage } from '@/store/defaultState'
import {
  ApiResponseType,
  ProfileImageType,
  ProfileInfoType,
  ProfileType,
} from '@/types/type'

const BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}`

/** 상대방 정보 받아오기 */
async function getAuthByUuid(
  uuid: string,
  token: string,
): Promise<(ApiResponseType & { result: ProfileType }) | undefined> {
  try {
    const response = await fetch(`${BASE_URL}/member/info/${uuid}`, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: token,
      },
    })
    if (!response.ok) {
      throw new Error('Failed to getAuthByUuid')
    }
    const data = await response.json()

    return data
  } catch (error) {
    console.error(error)
    return
  }
}

/** 프로필 정보 조회 - uuid */
async function getProfileInfoByUuidClient(
  uuid: string,
  token: string,
): Promise<(ApiResponseType & { result: ProfileInfoType }) | undefined> {
  try {
    const response = await fetch(`${BASE_URL}/profile/info/${uuid}`, {
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

async function getMainProfileImageByUuid(
  uuid: string,
  token: string,
): Promise<(ApiResponseType & { result: ProfileImageType }) | undefined> {
  try {
    const response = await fetch(`${BASE_URL}/profile/image/main/${uuid}`, {
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

/** uuid로 회원 정보 가져오기 */
export async function getQueueDataByUuid(uuid: string, token: string) {
  const authProfileData = getAuthByUuid(uuid, token)
  const profileInfoData = getProfileInfoByUuidClient(uuid, token)
  const mainProfileImageData = getMainProfileImageByUuid(uuid, token)

  const [result] = await Promise.all([
    Promise.all([authProfileData, profileInfoData, mainProfileImageData]).then(
      ([authProfile, profileInfo, mainProfileImage]) => ({
        uuid,
        authProfile: { ...authProfile?.result },
        profileInfo: { ...profileInfo?.result },
        mainProfileImage:
          mainProfileImage?.result?.profileImageUrl ?? defaultImage,
      }),
    ),
  ])

  return result
}
