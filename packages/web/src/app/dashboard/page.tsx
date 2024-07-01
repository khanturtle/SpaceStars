import { getExp, getLevel, getLevelInfo } from '@/apis/getLevel'
import { getMbtiById } from '@/apis/getMbti'
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

  return (
    <section className="flex-1 px-[50px] py-[42px] h-full overflow-auto">
      <div className="px-4 mx-auto max-w-7xl sm:px-6 lg:px-8">
        {/* Header Section */}
        <div className="relative">
          <img
            src="https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/BackGround/Swimming-Kirby.png"
            alt="Banner"
            className="inset-0 object-cover w-full h-full opacity-50 "
          />

          {/* Profile Section */}
          <div
            className="relative z-10 flex items-center justify-between py-4 "
            style={{
              position: 'absolute',
              top: '50%',
              transform: 'translateY(-50%)',
            }}
          >
            <div className="flex items-center space-x-6">
              <img
                src={data.mainProfileImage}
                alt="Profile"
                className="w-[200px] h-[200px] rounded-[15px] ml-4"
              />
              {/* badge */}
              <div className="flex-row">
                <div className="relative flex">
                  <span className="absolute bottom-1 left-[0px] bg-pink-400 text-white text-xs font-bold px-2 py-1 rounded-full">
                    {data.authProfile.gender}
                  </span>
                  <span className="absolute bottom-1 left-[70px] bg-pink-500 text-white text-xs font-bold px-2 py-1 rounded-full">
                    {mbtiData?.mbtiName}
                  </span>
                  <span className="absolute bottom-1 left-[100px] bg-pink-600 text-white text-xs font-bold px-2 py-1 rounded-full whitespace-nowrap">
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
          </div>
        </div>

        {/* Level Indicator */}
        <div className="mt-6">
          <div className="flex items-center justify-between text-[color:var(--text-title)]">
            <p className="text-sm">LV {level.level}</p>
            <p className="text-sm">
              {exp.levelExp} / {levelInfo?.levelTotalExp ?? 0}
            </p>
          </div>
          <div className="w-full bg-gray-700 rounded-full h-2.5 mt-1">
            <div
              className="bg-pink-600 h-2.5 rounded-full"
              style={{ width: expWidth }}
            ></div>
          </div>
        </div>

        {/* Game List */}
        <div className="grid grid-cols-1 gap-6 mt-6 lg:grid-cols-2">
          {data.playGames?.map((game, index) => (
            <>
              <div
                className="flex items-center p-4 space-x-4 bg-gray-800 rounded-lg"
                key={index}
              >
                <img
                  src="/league-image-url"
                  alt="League of Legends"
                  className="object-cover w-1/3 h-40 rounded-lg"
                />
                <div className="w-2/3">
                  <p className="text-lg font-semibold">{game.gameId}</p>
                  <p className="mt-2">서버 - {game.serverId}</p>
                  <p className="text-sm text-gray-400">티어 - {game.tierId}</p>
                </div>
              </div>
            </>
          ))}
        </div>
      </div>
    </section>
  )
}
/*
      <div>
        프로필 추가 테스트
        <ProfileImageUpload />
      </div>

 */
