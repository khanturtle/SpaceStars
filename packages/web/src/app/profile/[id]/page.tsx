import { getProfileByUuid } from '@/apis/getAuth'
import { getIsFriend } from '@/apis/getFriends'
import {
  getLikedGameByUuid,
  getPlayGameByUuid,
  getProfileInfoByUuid,
} from '@/apis/getProfileByUuid'
import {
  getMainProfileImageByUuid,
  getProfileImagesByUuid,
} from '@/apis/getProfileImage'
import Modal from '@/components/modal/modal'
import ProfileLayout from '@/containers/profile-modal/profileLayout'
import { defaultImage } from '@/store/defaultState'

// TODO: 모달로 이동

/** uuid로 모든 정보 받아오기 */
async function getAllProfileDataByUuid(uuid: string) {
  // 회원 정보
  const authProfileData = getProfileByUuid(uuid)
  const profileInfoData = getProfileInfoByUuid(uuid)
  const playGameData = getPlayGameByUuid(uuid)
  const likedGameIdsData = getLikedGameByUuid(uuid)

  // 대표 프로필 사진
  const mainProfileImageData = getMainProfileImageByUuid(uuid)
  const profileImagesData = getProfileImagesByUuid(uuid)

  // 친구 여부 확인
  const isFriendData = getIsFriend(uuid)

  const [result] = await Promise.all([
    Promise.all([
      authProfileData,
      profileInfoData,
      playGameData,
      likedGameIdsData,
      mainProfileImageData,
      profileImagesData,
      isFriendData,
    ]).then(
      ([
        authProfile,
        profileInfo,
        playGames,
        likedGameIds,
        mainProfileImage,
        profileImages,
        isFriend,
      ]) => ({
        authProfile: { ...authProfile?.result },
        profileInfo: { ...profileInfo?.result },
        playGames: playGames?.result,
        likedGameIds: likedGameIds?.result.likedGameIdList,
        mainProfileImage:
          mainProfileImage?.result?.profileImageUrl ?? defaultImage,
        profileImages: profileImages?.result,
        ...isFriend?.result,
      }),
    ),
  ])

  console.log(result)
  return result
}

export default async function Page({ params }: { params: { id: string } }) {
  // params.id 의 uuid를 가진 회원 정보 조회
  const targetUuid = params.id

  await getAllProfileDataByUuid(targetUuid)

  return (
    <Modal>
      <ProfileLayout />
    </Modal>
  )
}
