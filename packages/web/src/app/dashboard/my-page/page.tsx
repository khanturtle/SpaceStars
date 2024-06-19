import { getServerSession } from 'next-auth'

import { options } from '@/app/api/auth/[...nextauth]/options'

import { getAuthProfile } from '@/apis/getAuth'
import { getLikedGame, getPlayGame, getProfileInfo } from '@/apis/getProfile'
import { getMainProfileImage, getProfileImages } from '@/apis/getProfileImage'
import ProfileImageUpload from '@/containers/my-page/ProfileImageUpload'
import SearchUserContainer from '@/containers/search/SearchUserContainer'
import { defaultImage } from '@/store/defaultState'

async function getMyProfile(accessToken: string) {
  const authProfileData = getAuthProfile()
  const profileInfoData = getProfileInfo(accessToken)

  const playGameData = getPlayGame(accessToken)
  const likedGameIdsData = getLikedGame(accessToken)

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
        playGame,
        likedGameIds,
        mainProfileImage,
        profileImages,
      ]) => ({
        authProfile: { ...authProfile?.result },
        profileInfo: { ...profileInfo?.result },
        playGames: playGame?.result,
        likedGameIds: likedGameIds?.result.likedGameIdList,
        mainProfileImage:
          mainProfileImage?.result?.profileImageUrl ?? defaultImage,
        profileImages: profileImages?.result,
      }),
    ),
  ])

  return result
}

export default async function page() {
  const session = await getServerSession(options)
  const { accessToken } = session?.user?.data || {}

  const profileAll = await getMyProfile(accessToken)

  // TODO:
  return (
    <div>
      {JSON.stringify(profileAll)}
      <hr />
      <div>
        프로필 추가 테스트
        <ProfileImageUpload />
      </div>

      <div>
        회원 찾기
        <SearchUserContainer accessToken={accessToken} />
      </div>
    </div>
  )
}
