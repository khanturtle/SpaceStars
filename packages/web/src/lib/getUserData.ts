import { getProfileByUuid } from '@/apis/getAuth'
import { getMainProfileImageByUuid } from '@/apis/getProfileImage'
import { defaultImage } from '@/store/defaultState'

/** 대표 프로필, 닉네임 받아오기 */
export async function getBasicUserData(uuid: string) {
  const profileImageData = getMainProfileImageByUuid(uuid)
  const authProfileData = getProfileByUuid(uuid)

  const [result] = await Promise.all([
    Promise.all([profileImageData, authProfileData]).then(
      ([profileImage, authProfile]) => ({
        profileImageUrl: profileImage?.result.profileImageUrl ?? defaultImage,
        nickname: authProfile?.result?.nickname ?? '',
      }),
    ),
  ])
  return result
}
