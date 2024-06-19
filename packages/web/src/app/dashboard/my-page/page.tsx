import { options } from '@/app/api/auth/[...nextauth]/options'
import { getServerSession } from 'next-auth'

import { getAuthProfile } from '@/apis/getAuth'
import { getLikedGame, getPlayGame, getProfileInfo } from '@/apis/getProfile'
import { getMainProfileImage, getProfileImages } from '@/apis/getProfileImage'
import ProfileImageUpload from '@/containers/my-page/ProfileImageUpload'
import Image from 'next/image'
import SearchUserContainer from '@/containers/search/SearchUserContainer'
import { defaultImage } from '@/store/defaultState'

async function getMyProfile(accessToken: string) {
  const authProfileData = getAuthProfile()
  const profileInfoData = getProfileInfo(accessToken)
  const playGameData = getPlayGame(accessToken)
  const likedGameIdsData = getLikedGame(accessToken)

  const mainProfileImageData = getMainProfileImage()

  const [result] = await Promise.all([
    Promise.all([
      authProfileData,
      profileInfoData,
      playGameData,
      likedGameIdsData,
      mainProfileImageData,
    ]).then(
      ([
        authProfile,
        profileInfo,
        playGame,
        likedGameIds,
        mainProfileImage,
      ]) => ({
        authProfile: { ...authProfile?.result },
        profileInfo: { ...profileInfo?.result },
        playGames: playGame?.result,
        likedGameIds: likedGameIds?.result.likedGameIdList,
        mainProfileImage:
          mainProfileImage?.result?.profileImageUrl ?? defaultImage,
      }),
    ),
  ])

  return result
}

export default async function page() {
  const session = await getServerSession(options)
  const { accessToken } = session?.user?.data || {}

  const profileAll = await getMyProfile(accessToken)

  const profileImages = await getProfileImages()

  const defaultImage = '/images/default-images.jpg'

  // TODO:
  return (
    <div>
      {JSON.stringify(profileAll)}
      <hr />
      <div>
        프로필들: {JSON.stringify(profileImages)}
        {profileImages.map((item) => (
          <Image
            key={item.index}
            src={item.profileImageUrl || defaultImage}
            alt={item.profileImageUrl || defaultImage}
            width={30}
            height={30}
          />
        ))}
      </div>
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
