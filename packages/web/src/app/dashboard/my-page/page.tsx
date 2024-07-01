import { getServerSession } from 'next-auth'

import { options } from '@/app/api/auth/[...nextauth]/options'

import { getAllProfileData } from '@/lib/getAllProfileData'

export default async function page() {
  const session = await getServerSession(options)
  const { accessToken } = session?.user?.data || {}

  const allProfileData = await getAllProfileData()

  const data = allProfileData

  // TODO:
  return (
    <section className="flex-1 px-[50px] py-[42px] h-full overflow-auto">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        {/* Header Section */}
        <div className="relative py-4">
          <img
            src="https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/BackGround/Swimming-Kirby.png" // Replace with your banner image URL
            alt="Banner"
            className="absolute inset-0 w-full h-full object-cover opacity-50"
          />

          {/* Profile Section */}

          <div
            className="relative z-10 flex items-center justify-between py-4 border-b border-gray-700
          style={{ position: 'absolute', top: '50%', transform: 'translateY(-50%)' }}"
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
                    {data.profileInfo.mbtiId}
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
          <div className="flex items-center justify-between">
            <p className="text-sm">LV 25</p>
            <p className="text-sm">9 / 20</p>
          </div>
          <div className="w-full bg-gray-700 rounded-full h-2.5 mt-1">
            <div
              className="bg-pink-600 h-2.5 rounded-full"
              style={{ width: '45%' }}
            ></div>
          </div>
        </div>

        {/* Game List */}
        <div className="mt-6 grid grid-cols-1 lg:grid-cols-2 gap-6">
          {data.playGames?.map((game, index) => (
            <>
              {/* Game 1 */}
              <div className="bg-gray-800 rounded-lg p-4 flex items-center space-x-4">
                <img
                  src="/league-image-url"
                  alt="League of Legends"
                  className="w-1/3 h-40 object-cover rounded-lg"
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
{
 </div>
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
}
 */