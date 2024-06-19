import { getServerSession } from 'next-auth'

import { options } from '@/app/api/auth/[...nextauth]/options'

import ProfileImageUpload from '@/containers/my-page/ProfileImageUpload'
import SearchUserContainer from '@/containers/search/SearchUserContainer'
import { getAllProfileData } from '@/lib/getAllProfileData'

export default async function page() {
  const session = await getServerSession(options)
  const { accessToken } = session?.user?.data || {}

  const allProfileData = await getAllProfileData()

  // TODO:
  return (
    <div>
      {JSON.stringify(allProfileData)}
      <hr />
      <div>
        프로필 추가 테스트
        <ProfileImageUpload />
      </div>

      <div>
        회원 찾기 테스트
        <SearchUserContainer accessToken={accessToken} />
      </div>
    </div>
  )
}
