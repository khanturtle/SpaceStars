import { options } from '@/app/api/auth/[...nextauth]/options'
import { getServerSession } from 'next-auth'

import { getAuthProfile } from '@/apis/getAuth'
import { getLikedGame, getPlayGame, getProfileInfo } from '@/apis/getProfile'
import { getMainProfileImg, getProfileImages } from '@/apis/getProfileImage'
import ProfileImageUpload from '@/containers/my-page/ProfileImageUpload'
import Image from 'next/image'
import SearchUserContainer from '@/containers/search/SearchUserContainer'

async function getMyProfile(accessToken: string) {
  const authProfileData = getAuthProfile()
  const profileInfoData = getProfileInfo(accessToken)
  const playGameData = getPlayGame(accessToken)
  const likedGameIdsData = getLikedGame(accessToken)

  const [result] = await Promise.all([
    Promise.all([
      authProfileData,
      profileInfoData,
      playGameData,
      likedGameIdsData,
    ]).then(([authProfile, profileInfo, playGame, likedGameIds]) => ({
      authProfile: { ...authProfile?.result },
      profileInfo: { ...profileInfo?.result },
      playGames: playGame?.result,
      likedGameIds: likedGameIds?.result.likedGameIdList,
    })),
  ])

  // console.log(result)
  return result
}

export default async function page() {
  const session = await getServerSession(options)
  const { accessToken } = session?.user?.data || {}

  const profileAll = await getMyProfile(accessToken)

  const mainProfileImg = await getMainProfileImg()
  const profileImages = await getProfileImages()

  const defaultImage = '/images/default-images.jpg'

  // TODO:
  return (
    <div>
      {JSON.stringify(profileAll)}
      <hr />
      <div>대표 프로필: {JSON.stringify(mainProfileImg)}</div>
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
