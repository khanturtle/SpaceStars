import { options } from '@/app/api/auth/[...nextauth]/options'
import { getServerSession } from 'next-auth'

import { getProfile } from '@/apis/auth-member'
import { getLikedGame, getPlayGame, getProfileInfo } from '@/apis/profile'
import { getMainProfileImg, getProfileImages } from '@/apis/getProfileImage'
import ProfileImageUpload from '@/containers/my-page/ProfileImageUpload'
import Image from 'next/image'
import SearchUserContainer from '@/containers/search/SearchUserContainer'

export default async function page() {
  const session = await getServerSession(options)
  const { accessToken } = session?.user?.data || {}

  const auth = await getProfile()
  const member = await getProfileInfo(accessToken)

  const playGame = await getPlayGame(accessToken)
  const likedGame = await getLikedGame(accessToken)

  const mainProfileImg = await getMainProfileImg()
  const profileImages = await getProfileImages()

  // TODO:
  return (
    <div>
      <div>auth: {JSON.stringify(auth)}</div>
      <div>Member: {JSON.stringify(member)}</div>
      <div>내가하는 게임: {JSON.stringify(playGame)}</div>
      <div>좋아하는 게임: {JSON.stringify(likedGame)}</div>

      <div>대표 프로필: {JSON.stringify(mainProfileImg)}</div>
      <div>
        프로필들: {JSON.stringify(profileImages)}
        {profileImages.map((item) => (
          <Image
            key={item.index}
            src={item.profileImageUrl}
            alt={item.profileImageUrl}
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
