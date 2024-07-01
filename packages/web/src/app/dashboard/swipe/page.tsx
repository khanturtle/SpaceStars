import { getServerSession } from 'next-auth'

import { options } from '@/app/api/auth/[...nextauth]/options'

import { getMbtiById } from '@/apis/getMbti'
import { getSwipeList } from '@/apis/getSwipe'

import BackGroundTextBox from '@/components/Background/BackGroundTextBox'
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

async function fetchAllMBTI(profileDataList: any) {
  const results = []

  for (const profile of profileDataList) {
    try {
      const mbtiName = await getMbtiById(profile.profileInfo.mbtiId)
      results.push(mbtiName?.mbtiName ?? '')
    } catch (error) {
      console.error(error)
    }
  }
  return results
}

/** 레벨 조회 */
async function getLevel(uuid: string) {
  const session = await getServerSession(options)
  const token = session?.user?.data.accessToken

  try {
    const response = await fetch(
      `${process.env.NEXT_PUBLIC_API_URL_V1}/level/${uuid}`,
      {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          Authorization: token ? token : '',
        },
      },
    )
    if (!response.ok) {
      throw new Error('Failed to getLevel')
    }

    const data = await response.json()
    console.log(data)
    return data
  } catch (e) {
    console.error(e)
    return
  }
}

export default async function page({
  searchParams,
}: {
  searchParams: { [key: string]: string }
}) {
  const currentPage = searchParams.page ? Number(searchParams.page) : 0

  const swipeData = await getSwipeList(4, currentPage)

  const profileDataList = await Promise.all(
    swipeData?.memberUuidList.map(async (uuid: string) => {
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
  const MBTINames = await fetchAllMBTI(profileDataList)
  // const level = await getLevel()

  return (
    <div className="relative flex items-center justify-center w-full h-full">
      <RefreshButton nextPage={nextPage} />

      <SwipeCardWrapper
        profileDataList={profileDataList}
        playGames={playGames}
        MBTINames={MBTINames}
      />

      <BackGroundTextBox text="NEXT GAMER MATCHING" />
    </div>
  )
}
