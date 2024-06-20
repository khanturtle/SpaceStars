import { getFriendsList } from '@/apis/getFriends'
import { FriendsListType } from '@/types/type'

import { getBasicUserData } from './getUserData'

export interface friendsWithBasicDataType extends FriendsListType {
  profileImageUrl: string
  nickname: string
}

/** 내 친구 목록 - 대표 프로필, 닉네임 */
export async function getFriendsDataList(): Promise<
  friendsWithBasicDataType[]
> {
  const friendsListResult = await getFriendsList()
  const friendsList = friendsListResult?.result
  if (!friendsList) return []

  const friendsWithBasicData = await Promise.all(
    friendsList.map(async (friend) => {
      const data = await getBasicUserData(friend.friendUuid)
      return { ...friend, ...data }
    }),
  )

  return friendsWithBasicData
}
