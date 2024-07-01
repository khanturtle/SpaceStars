import { getServerSession } from 'next-auth'

import { options } from '../api/auth/[...nextauth]/options'

import { getGames } from '@/apis/getGame'
import { getExp, getLevel, getLevelInfo } from '@/apis/getLevel'

import { DevModalOpen } from '@/containers/additional-details/AdditionalDetailsLayout'
import {
  RecommendedFriends,
  MyChatRooms,
} from '@/containers/dashboard/components'
import UserProfile from '@/containers/dashboard/UserProfile'
import Card from '@/containers/dashboard/Card'
import GameSelector from '@/containers/dashboard/Games'

import { getAllProfileData } from '@/lib/getAllProfileData'

export default async function page() {
  const session = await getServerSession(options)
  const { accessToken } = session?.user?.data || {}

  const allProfileData = await getAllProfileData()

  const level = (await getLevel()) ?? 0
  const exp = (await getExp()) ?? 0
  const levelInfo = (await getLevelInfo(level.level)) ?? null

  const games = await getGames()

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
          <div className="w-full lg:w-auto">
            <Card />
          </div>

          <div className="flex-1">
            <GameSelector games={games} />
            <div className="grid grid-cols-1 gap-8 md:grid-cols-2">
              <MyChatRooms />
              <RecommendedFriends />
            </div>
          </div>
        </div>
      </section>

      <DevModalOpen />
    </>
  )
}
