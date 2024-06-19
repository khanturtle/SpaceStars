import { getAuthProfile, getProfileByUuid } from '@/apis/getAuth'
import { getLikedGame, getPlayGame, getProfileInfo } from '@/apis/getProfile'
import {
  getLikedGameByUuid,
  getPlayGameByUuid,
  getProfileInfoByUuid,
} from '@/apis/getProfileByUuid'
import {
  getMainProfileImage,
  getProfileImages,
  getMainProfileImageByUuid,
  getProfileImagesByUuid,
} from '@/apis/getProfileImage'
import { getFriendType } from '@/apis/getFriends'

import { defaultImage } from '@/store/defaultState'

/** 회원 정보 모두 가져오기 */
export async function getAllProfileData() {
  const authProfileData = getAuthProfile()
  const profileInfoData = getProfileInfo()

  const playGameData = getPlayGame()
  const likedGameIdsData = getLikedGame()

  const mainProfileImageData = getMainProfileImage()
  const profileImagesData = getProfileImages()

  const [result] = await Promise.all([
    Promise.all([
      authProfileData,
      profileInfoData,
      playGameData,
      likedGameIdsData,
      mainProfileImageData,
      profileImagesData,
    ]).then(
      ([
        authProfile,
        profileInfo,
        playGames,
        likedGameIds,
        mainProfileImage,
        profileImages,
      ]) => ({
        authProfile: { ...authProfile?.result },
        profileInfo: { ...profileInfo?.result },
        playGames: playGames?.result,
        likedGameIds: likedGameIds?.result.likedGameIdList,
        mainProfileImage:
          mainProfileImage?.result?.profileImageUrl ?? defaultImage,
        profileImages: profileImages?.result,
      }),
    ),
  ])

  return result
}

/** uuid로 회원 정보 모두 가져오기 */
export async function getAllProfileDataByUuid(uuid: string) {
  const authProfileData = getProfileByUuid(uuid)
  const profileInfoData = getProfileInfoByUuid(uuid)
  const playGameData = getPlayGameByUuid(uuid)
  const likedGameIdsData = getLikedGameByUuid(uuid)

  const mainProfileImageData = getMainProfileImageByUuid(uuid)
  const profileImagesData = getProfileImagesByUuid(uuid)

  // 친구 여부 확인
  const friendTypeData = getFriendType(uuid)

  const [result] = await Promise.all([
    Promise.all([
      authProfileData,
      profileInfoData,
      playGameData,
      likedGameIdsData,
      mainProfileImageData,
      profileImagesData,
      friendTypeData,
    ]).then(
      ([
        authProfile,
        profileInfo,
        playGames,
        likedGameIds,
        mainProfileImage,
        profileImages,
        friendType,
      ]) => ({
        authProfile: { ...authProfile?.result },
        profileInfo: { ...profileInfo?.result },
        playGames: playGames?.result,
        likedGameIds: likedGameIds?.result.likedGameIdList,
        mainProfileImage:
          mainProfileImage?.result?.profileImageUrl ?? defaultImage,
        profileImages: profileImages?.result,
        ...friendType?.result,
      }),
    ),
  ])

  return result
}
