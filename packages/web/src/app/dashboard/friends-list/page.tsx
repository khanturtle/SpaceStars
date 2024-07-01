import FriendTabs from '@/containers/friends-list/FriendTabs'
import { FriendsListType } from '@/types/type'
import { getServerSession } from 'next-auth'
import { options } from '@/app/api/auth/[...nextauth]/options'
import { getBasicUserData } from '@/lib/getUserData'
import FriendListTable from '@/containers/friends-list/FriendListTable'
import SearchBox from '@/components/search/SearchBox'

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
  const query: any = searchParams
  if (!query || query === undefined) return null

  const session = await getServerSession(options)
  const token = session?.user?.data.accessToken

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

  return (
    <section className="flex-1 px-[50px] py-[42px] h-full overflow-auto">
      <div className="flex justify-end items-center mb-8">
        <SearchBox />
      </div>
      <FriendTabs type={query.type} />
      <FriendListTable data={friendsWithBasicData} />
    </section>
  )
}
