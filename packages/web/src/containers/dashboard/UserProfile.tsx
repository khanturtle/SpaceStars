export default function UserProfile({
  data,
  level,
  exp,
  levelInfo,
}: {
  data: any
  level: number
  exp: number
  levelInfo: any
}) {
  const expWidth = (exp / levelInfo?.levelTotalExp) * 100

  return (
    <section className="flex-1">
      <div className="px-4 mx-auto max-w-7xl sm:px-6 lg:px-8">
        <div className="relative h-[33vh]">
          <img
            src="https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/BackGround/Swimming-Kirby.png"
            alt="Banner"
            className="absolute inset-0 object-cover w-full h-full opacity-50"
          />
          <div className="absolute inset-0 flex flex-col justify-center px-4 sm:px-6 lg:px-8">
            <div className="flex items-center mb-4 space-x-6">
              <img
                src={data.mainProfileImage}
                alt="Profile"
                className="object-cover w-20 h-20 border-2 border-white rounded-full"
              />
              <div>
                <h2 className="text-2xl font-bold text-[color:var(--text-title)]">
                  {data.authProfile.nickname ?? ''}
                </h2>
                <p className="text-sm text-[color:var(--text-desc)]">
                  {data.profileInfo.introduction ?? ''}
                </p>
              </div>
            </div>

            <div className="p-4 bg-black bg-opacity-50 rounded-lg">
              <div className="flex items-center justify-between text-white">
                <p className="text-sm">LV {level}</p>
                <p className="text-sm">
                  {exp} / {levelInfo?.levelTotalExp ?? 0}
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
    </section>
  )
}
