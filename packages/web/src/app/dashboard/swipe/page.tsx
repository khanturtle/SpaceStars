import { getSwipeList } from '@/apis/getSwipe'

import BackGroundTextBox from '@/components/Background/BackGroundTextBox'
import NavHeader from '@/components/Navbar/NavHeader'
import RefreshButton from '@/containers/swipe/RefreshButton'
import SwipeCardWrapper from '@/containers/swipe/SwipeCardWrapper'
import { getAllProfileDataByUuid } from '@/lib/getAllProfileData'

export default async function page({
  searchParams,
}: {
  searchParams: { [key: string]: string }
}) {
  const currentPage = searchParams.page ? Number(searchParams.page) : 0

  const swipeData = await getSwipeList(4, currentPage)

  const profileDataList = await Promise.all(
    swipeData.memberUuidList.map(async (uuid: string) => {
      try {
        return await getAllProfileDataByUuid(uuid)
      } catch (error) {
        console.error(`Error profileDataList: ${uuid}`, error)
        return null
      }
    }),
  )

  const nextPage = swipeData && swipeData.last ? 0 : swipeData.nowPage + 1

  // console.log(profileDataList)

  return (
    // FIXME: 배경색 수정
    <div className="relative w-full h-full flex items-center justify-center bg-[#18243a]">
      <NavHeader
        title="SWIPE"
        description="게임을 같이 할 나의 친구를 찾아 드려요!"
      />
      <RefreshButton nextPage={nextPage} />

      <SwipeCardWrapper profileDataList={profileDataList} />

      <BackGroundTextBox text="NEXT GAMER MATCHING" />
    </div>
  )
}
