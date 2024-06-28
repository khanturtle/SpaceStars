import FriendTabs from '@/containers/friends-list/FriendTabs'
import { SearchInput } from '@packages/ui'
import { FriendsListType } from '@/types/type'
import { getServerSession } from 'next-auth'
import { options } from '@/app/api/auth/[...nextauth]/options'
import { getBasicUserData } from '@/lib/getUserData'
import FriendListTable from '@/containers/friends-list/FriendListTable'

export interface dataType {
  index: number
  friendUuid: string
  nickname: string
  profileImageUrl: string
}

export default async function Page({
  searchParams,
}: {
  searchParams: { [key: string]: string | 'all' }
}) {
  console.log(searchParams)
  const query: any = searchParams
  if (!query || query === undefined) return null
  console.log(query.type)
  const session = await getServerSession(options)
  const token = session?.user?.data.accessToken
  console.log(token)

  // Fetch friends list using the token and query type
  const response = await fetch(
    `https://spacestars.kr/api/v1/friend/list?type=${query.type}`,
    {
      headers: {
        'Content-Type': 'application/json',
        Authorization: token ? token : '',
      },
      cache: 'no-cache',
    },
  )

  if (!response.ok) {
    console.error('Failed to fetch friends list')
    return null
  }

  const data = await response.json()
  const friendsData: FriendsListType[] = data.result
  if (!friendsData) return null
  const friendsWithBasicData: dataType[] = await Promise.all(
    friendsData.map(async (friend: FriendsListType) => {
      const data = await getBasicUserData(friend.friendUuid)
      return { ...friend, ...data }
    }),
  )
  console.log(friendsWithBasicData, 'friendsWithBasicData')

  return (
    <section className="flex-1 px-[50px] py-[42px] h-full overflow-auto">
      <div className="flex justify-between items-center mb-8">
        <div className="text-neutral-800 text-4xl font-medium font-['Poppins'] leading-[48px]">
          Friends
        </div>
        <div className="flex items-center space-x-2">
          <SearchInput className="h-14" placeholder="Search people" />
          <button className="bg-transparent hover:bg-purple-600 text-purple-600 font-semibold hover:text-white py-2 px-3 border border-purple-600 hover:border-transparent rounded">
            Search
          </button>
        </div>
      </div>
      {/* FriendTabs를 클라이언트 측에서 렌더링 */}
      <FriendTabs type={query.type} />
      <FriendListTable data={friendsWithBasicData} />
    </section>
  )
}
