import Image from 'next/image'

import { getExp, getLevel, getLevelInfo } from '@/apis/getLevel'
import { getMbtiById } from '@/apis/getMbti'
import ProfileButton from '@/containers/my-page/profileButton'

import { fetchPlayGames } from '@/lib/fetchGamesData'
import { getAllProfileData } from '@/lib/getAllProfileData'

export default async function page() {
  const allProfileData = await getAllProfileData()
  const data = allProfileData

  const level = (await getLevel()) ?? 0
  const exp = (await getExp()) ?? 0
  const levelInfo = (await getLevelInfo(level.level)) ?? null

  const expWidth = (exp.levelExp / levelInfo?.levelTotalExp) * 100

  const mbtiData =
    (await getMbtiById(data.profileInfo.mbtiId as number)) ?? null

  const gameData = await fetchPlayGames(data.playGames ?? [])

  return (
    <section className="flex-1">
      <div className="px-4 mx-auto max-w-7xl sm:px-6 lg:px-8">
        <div className="relative h-[33vh]">
          <img
            src="https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/BackGround/Swimming-Kirby.png"
            alt="Banner"
            className="absolute inset-0 object-cover w-full h-full opacity-50"
          />
          <div className="absolute bottom-[10px] flex flex-col w-full px-4 sm:px-6 lg:px-8">
            <div className="flex items-center mb-4 space-x-6">
              <ProfileButton image={data?.mainProfileImage ?? ''} />

              <div className="flex-row">
                <div className="relative flex">
                  <span className="px-2 py-1 mr-2 text-xs font-bold text-white bg-pink-400 rounded-full">
                    {data.authProfile.gender}
                  </span>
                  <span className="px-2 py-1 mr-2 text-xs font-bold text-white bg-pink-500 rounded-full">
                    {mbtiData?.mbtiName}
                  </span>
                  <span className="px-2 py-1 text-xs font-bold text-white bg-pink-600 rounded-full whitespace-nowrap">
                    {data.authProfile.birth}
                  </span>
                </div>
                <div>
                  <h2 className="text-2xl font-bold">
                    {data.authProfile.nickname}
                  </h2>

                  <p className="text-sm text-gray-400">
                    {data.profileInfo.introduction}
                  </p>
                </div>
              </div>
            </div>

            <div className="p-4 bg-black bg-opacity-50 rounded-lg">
              <div className="flex items-center justify-between text-white">
                <p className="text-sm">LV {level.level}</p>
                <p className="text-sm">
                  {exp.levelExp} / {levelInfo?.levelTotalExp ?? 0}
                </p>
              </div>
              <div className="w-full bg-gray-700 rounded-full h-2.5 mt-1">
                <div
                  className="bg-pink-600 h-2.5 rounded-full"
                  style={{ width: expWidth }}
                />
              </div>
            </div>
          </div>
        </div>
      </div>

      <div className="p-[32px] grid grid-cols-1 gap-6 mt-6 lg:grid-cols-2">
        {gameData?.map((game, index) => (
          <div key={index}>
            <div className="w-[398px] h-[103px] flex items-center p-4 space-x-4 bg-gray-800 border rounded-[0px_12px_12px_12px] border-solid border-[rgba(255,250,243,0.5)] relative">
              <Image
                src={game?.gameInfo?.gameImage ?? ''}
                alt={game?.gameInfo?.gameName ?? ''}
                fill
                className="object-cover w-1/3 h-40 rounded-lg"
              />
            </div>

            <div className="w-2/3 mt-[4px] ml-[4px]">
              <p className="text-lg font-semibold text-[color:var(--text-title)]">
                {game.gameInfo?.gameNameKor}
              </p>
              <div className="flex gap-[8px]">
                {game.optionInfo.map((info, index) => (
                  <div key={index} className="flex">
                    <img
                      src={info?.image ?? ''}
                      alt={info?.id ?? ''}
                      className="w-[20px] h-[20px] mr-1"
                    />
                    <p className="text-[#b779ff] text-sm not-italic font-semibold leading-[normal]">
                      {info?.nameKor ?? ''}
                    </p>
                  </div>
                ))}
              </div>
            </div>
          </div>
        ))}
      </div>
    </section>
  )
}
