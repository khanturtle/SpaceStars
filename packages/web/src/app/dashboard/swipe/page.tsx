import { getSwipeList } from '@/apis/getSwipe'

import BackGroundTextBox from '@/components/Background/BackGroundTextBox'
import NavHeader from '@/components/Navbar/NavHeader'
import RefreshButton from '@/containers/swipe/RefreshButton'
import SwipeCardWrapper from '@/containers/swipe/SwipeCardWrapper'

import { fetchPlayGames } from '@/lib/fetchGamesData'
import { getAllProfileDataByUuid } from '@/lib/getAllProfileData'

async function fetchAllPlayGames(profileDataList: any) {
  const results = []

  for (const profile of profileDataList) {
    try {
      const gameData = await fetchPlayGames(profile.playGames)
      results.push(gameData)
    } catch (error) {
      console.error(error)
    }
  }

  return results
}

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

  const playGames = await fetchAllPlayGames(profileDataList)

  return (
    // FIXME: 배경색 수정
    <div className="relative w-full h-full flex items-center justify-center bg-[#18243a]">
      <NavHeader
        title="SWIPE"
        description="게임을 같이 할 나의 친구를 찾아 드려요!"
      />
      <RefreshButton nextPage={nextPage} />

      <SwipeCardWrapper
        profileDataList={profileDataList}
        playGames={playGames}
      />

      <BackGroundTextBox text="NEXT GAMER MATCHING" />
    </div>
  )
}
