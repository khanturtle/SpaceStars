import { getServerSession } from 'next-auth'

import { options } from '../api/auth/[...nextauth]/options'

import { DevModalOpen } from '@/containers/additional-details/AdditionalDetailsLayout'
import {
  RecommendedFriends,
  MyChatRooms,
} from '@/containers/dashboard/components'
import { getAllProfileData } from '@/lib/getAllProfileData'
import UserProfile from '@/containers/dashboard/UserProfile'
import Card from '@/containers/dashboard/Card'
import GameSelector from '@/containers/dashboard/Games'
import { getGames } from '@/apis/getGame'
import { getSwipeList } from '@/apis/getSwipe'
import { getBasicUserData } from '@/lib/getUserData'

/** 레벨 조회 */
async function getLevel() {
  const session = await getServerSession(options)
  const { accessToken } = session?.user?.data || {}

  try {
    const response = await fetch(`${process.env.NEXT_PUBLIC_API_URL_V1}/rate`, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: accessToken,
      },
    })

    if (!response.ok) {
      throw new Error('Failed to getLevel')
    }

    const data = await response.json()
    return data.result
  } catch (error) {
    console.error(error)
    return
  }
}

/** 레벨 조회 */
async function getExp() {
  const session = await getServerSession(options)
  const { accessToken } = session?.user?.data || {}

  try {
    const response = await fetch(
      `${process.env.NEXT_PUBLIC_API_URL_V1}/rate/exp`,
      {
        headers: {
          'Content-Type': 'application/json',
          Authorization: accessToken,
        },
      },
    )

    if (!response.ok) {
      throw new Error('Failed to getLevel')
    }

    const data = await response.json()
    return data.result
  } catch (error) {
    console.error(error)
    return
  }
}

async function getLevelInfo(level: number) {
  const session = await getServerSession(options)
  const { accessToken } = session?.user?.data || {}

  try {
    const response = await fetch(
      `${process.env.NEXT_PUBLIC_API_URL_V1}/rate/info?level=${level}`,
      {
        headers: {
          'Content-Type': 'application/json',
          Authorization: accessToken,
        },
      },
    )

    if (!response.ok) {
      throw new Error('Failed to getLevelInfo')
    }

    const data = await response.json()
    return data.result
  } catch (error) {
    console.error(error)
    return
  }
}

/** 스와이프 2명 */

export default async function page() {
  const session = await getServerSession(options)
  const { accessToken } = session?.user?.data || {}

  const allProfileData = await getAllProfileData()

  const level = (await getLevel()) ?? 0
  const exp = (await getExp()) ?? 0
  const levelInfo = (await getLevelInfo(level.level)) ?? null

  const games = await getGames()

  const swipeData = await getSwipeList(2, 0)
  const profileDataList = await Promise.all(
    swipeData?.memberUuidList.map(async (uuid: string) => {
      try {
        return await getBasicUserData(uuid)
      } catch (error) {
        console.error(`Error getSwipeList: ${uuid}`, error)
        return null
      }
    }),
  )

  console.log(profileDataList)
  return (
    <>
      <section className="flex-1 overflow-y-scroll">
        <UserProfile
          data={allProfileData}
          level={level.level}
          exp={exp.levelExp}
          levelInfo={levelInfo}
        />

        <div className="flex flex-col gap-8 px-5 mt-8 lg:flex-row">
          <div className="">
            <GameSelector games={games} />
          </div>

          <div className="lg:w-1/2">
            <div className="flex flex-col gap-8">
              <MyChatRooms />
              <RecommendedFriends profileDataList={profileDataList} />
            </div>
          </div>

          <div className="lg:w-1/4">
            <Card />
          </div>
        </div>
      </section>

      <DevModalOpen />
    </>
  )
}
