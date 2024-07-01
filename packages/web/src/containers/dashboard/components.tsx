const RecommendedFriends = () => {
  return (
    <div className="bg-white p-6 rounded-lg shadow-md">
      <h2 className="text-xl font-semibold mb-4">추천 친구</h2>
      <ul className="space-y-2">
        <li className="flex justify-between items-center">
          <span>홍길동</span>
          <button className="bg-green-500 text-white px-3 py-1 rounded">
            친구 추가
          </button>
        </li>
        <li className="flex justify-between items-center">
          <span>김철수</span>
          <button className="bg-green-500 text-white px-3 py-1 rounded">
            친구 추가
          </button>
        </li>
      </ul>
    </div>
  )
}

const MyChatRooms = () => {
  return (
    <div className="bg-white p-6 rounded-lg shadow-md">
      <h2 className="text-xl font-semibold mb-4">내 채팅방</h2>
      <ul className="space-y-2">
        <li className="flex justify-between items-center">
          <span>프로젝트 논의방</span>
          <span className="text-xs bg-red-500 text-white px-2 py-1 rounded-full">
            2
          </span>
        </li>
        <li className="flex justify-between items-center">
          <span>팀 채팅방</span>
          <span className="text-xs bg-gray-300 text-gray-800 px-2 py-1 rounded-full">
            0
          </span>
        </li>
      </ul>
    </div>
  )
}


export { RecommendedFriends, MyChatRooms }
