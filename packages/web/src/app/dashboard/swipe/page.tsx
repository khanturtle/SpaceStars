import { getSwipeList } from '@/apis/getSwipe'

import BackGroundTextBox from '@/components/Background/BackGroundTextBox'
import NavHeader from '@/components/Navbar/NavHeader'
import RefreshButton from '@/containers/swipe/RefreshButton'
import SwipeCardWrapper from '@/containers/swipe/SwipeCardWrapper'

export default async function page({
  searchParams,
}: {
  searchParams: { [key: string]: string }
}) {
  const currentPage = searchParams.page ? Number(searchParams.page) : 0

  const swipeData = await getSwipeList(4, currentPage)
  console.log(currentPage, swipeData)

  const nextPage = swipeData && swipeData.last ? 0 : swipeData.nowPage + 1

  return (
    <div className="relative w-full ">
      <BackGroundTextBox text="NEXT GAMER MATCHING" />
      <NavHeader
        title="SWIPE"
        description="게임을 같이 할 나의 친구를 찾아 드려요!"
      />
      <RefreshButton nextPage={nextPage} />

      <SwipeCardWrapper />
    </div>
  )
}
