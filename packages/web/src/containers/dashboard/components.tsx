import Link from 'next/link'

const RecommendedFriends = ({
  profileDataList,
}: {
  profileDataList: {
    profileImageUrl: string
    nickname: string
  }[]
}) => {
  return (
    <div className="p-6  bg-[color:var(--bg-primary)] text-[color:var(--text-title)] rounded-lg shadow-md">
      <h2 className="mb-4 text-xl font-semibold">추천 친구</h2>
      <ul className="space-y-2">
        {profileDataList &&
          profileDataList.map((item) => (
            <li className="flex items-center justify-between">
              <span>{item.nickname ?? ''}</span>
              <Link
                href="/dashboard/swipe"
                className="px-3 py-1 text-white bg-green-500 rounded"
              >
                친구 요청
              </Link>
            </li>
          ))}
      </ul>
    </div>
  )
}

const MyChatRooms = () => {
  return (
    <div className="p-6 bg-[color:var(--bg-primary)] text-[color:var(--text-title)] rounded-lg shadow-md">
      <h2 className="mb-4 text-xl font-semibold">내 채팅방</h2>
      <ul className="space-y-2">
        <li className="flex items-center justify-between">
          <span>개인 채팅방</span>
          <span className="px-2 py-1 text-xs text-white bg-red-500 rounded-full">
            2
          </span>
        </li>
        <li className="flex items-center justify-between">
          <span>팀 채팅방</span>
          <span className="px-2 py-1 text-xs text-gray-800 bg-gray-300 rounded-full">
            0
          </span>
        </li>
      </ul>
    </div>
  )
}

export { RecommendedFriends, MyChatRooms }
