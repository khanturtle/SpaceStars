export interface FriendsListType {
  index: number
  friendUuid: string
}

export interface friendsWithBasicDataType extends FriendsListType {
  profileImageUrl: string
  nickname: string
}

async function getBasicUserData(friendUuid: string) {
  const response = await fetch(
    `https://spacestars.kr/api/v1/user/${friendUuid}`,
  )
  const data = await response.json()
  return data
}

export async function getFriendsDataList(
  friendsList: FriendsListType[],
): Promise<friendsWithBasicDataType[]> {
  if (!friendsList) return []

  const friendsWithBasicData = await Promise.all(
    friendsList.map(async (friend) => {
      const data = await getBasicUserData(friend.friendUuid)
      return { ...friend, ...data }
    }),
  )

  return friendsWithBasicData
}
