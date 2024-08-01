import {
  getFriendsList,
  getFriendsRequestList,
  getFriendsSendList,
} from '@/apis/getFriends'
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

/** 친구 보낸 요청 목록 */
export async function getFriendsSendDataList(): Promise<
  friendsWithBasicDataType[]
> {
  const friendsListResult = await getFriendsSendList()
  const friendsList = friendsListResult?.result
  if (!friendsList) return []

  const friendsWithBasicData = await Promise.all(
    friendsList.map(async (friend: any) => {
      const data = await getBasicUserData(friend.friendSendUuid)
      return { ...friend, ...data }
    }),
  )

  return friendsWithBasicData
}

/** 친구 받은 요청 목록 */
export async function getFriendsRequestDataList(): Promise<
  friendsWithBasicDataType[]
> {
  const friendsListResult = await getFriendsRequestList()
  const friendsList = friendsListResult?.result
  if (!friendsList) return []

  const friendsWithBasicData = await Promise.all(
    friendsList.map(async (friend: any) => {
      const data = await getBasicUserData(friend.friendRequestUuid)
      return { ...friend, ...data }
    }),
  )

  return friendsWithBasicData
}
