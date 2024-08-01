import { defaultImage } from '@/store/defaultState'
import { ChatRoomMemberType } from '@/types/type'

const CHAT_BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/chat`
const MEMBER_BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/member`
const BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/profile/image`

/** 대표 프로필 조회 - UUID */
export async function getMainProfileImageByUuid(uuid: string, token: string) {
  try {
    const response = await fetch(`${BASE_URL}/main/${uuid}`, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: token ? token : '',
      },
    })
    if (!response.ok) {
      throw new Error('Failed to getMainProfileImageByUuid')
    }
    return await response.json()
  } catch (error) {
    console.error(error)
    return undefined
  }
}

/** 상대방 정보 받아오기 */
export async function getProfileByUuid(uuid: string, token: string) {
  try {
    const response = await fetch(`${MEMBER_BASE_URL}/info/${uuid}`, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: token ? token : '',
      },
    })
    if (!response.ok) {
      throw new Error('Failed to getProfileByUuid')
    }

    return await response.json()
  } catch (error) {
    console.error(error)
    return
  }
}

/** 대표 프로필, 닉네임 받아오기 */
export async function getBasicUserData(uuid: string, token: string) {
  const profileImageData = getMainProfileImageByUuid(uuid, token)
  const authProfileData = getProfileByUuid(uuid, token)

  const [result] = await Promise.all([
    Promise.all([profileImageData, authProfileData]).then(
      ([profileImage, authProfile]) => ({
        profileImageUrl: profileImage?.result?.profileImageUrl ?? defaultImage,
        nickname: authProfile?.result?.nickname ?? '',
      }),
    ),
  ])
  return result
}

/** 1:1 방 참여자 조회 */
export async function getRoomMember(
  roomUuid: string,
  token: string,
): Promise<ChatRoomMemberType[]> {
  try {
    const response = await fetch(
      `${CHAT_BASE_URL}/one-to-one/chatroom/${roomUuid}`,
      {
        headers: {
          'Content-Type': 'application/json',
          Authorization: token ? token : '',
        },
      },
    )

    if (!response.ok) {
      throw new Error('Failed to getRoomDetail')
    }
    const data = await response.json()
    return data.result
  } catch (err) {
    // console.error(err)
    return []
  }
}

/** 나의 1:1 채팅방 중 하나의 정보를 담아 가져오기
 *  방 참여자 정보: uuid, 프로필사진, 닉네임
 */
export type MemberInfoType = {
  profileImageUrl: string
  nickname: string
  index: number
  memberUuid: string
}
export async function getChatroomData(
  roomNumber: string,
  token: string,
): Promise<MemberInfoType[]> {
  // 방 참여자 목록
  const member = await getRoomMember(roomNumber, token)

  if (!member || member.length === 0) {
    return []
  }

  // 방 참여자의 프로필사진, 닉네임
  const chatMemberInfos = await Promise.all(
    member.map(async (m) => {
      const memberInfo = await getBasicUserData(m.memberUuid, token)
      return {
        ...m,
        ...memberInfo,
      }
    }),
  )
  return chatMemberInfos
}

/** 팀채팅 방 참여자 조회 */
async function getTeamRoomMember(roomUuid: string, token: string) {
  try {
    const response = await fetch(
      `${CHAT_BASE_URL}/team/chatroom/${roomUuid}/members`,
      {
        headers: {
          'Content-Type': 'application/json',
          Authorization: token ? token : '',
        },
      },
    )

    if (!response.ok) {
      throw new Error('Failed to getTeamRoomMember')
    }
    const data = await response.json()
    return data.result
  } catch (err) {
    // console.error(err)
    return []
  }
}

/** 나의 그룹 채팅방 중 하나의 정보를 담아 가져오기
 *  방 참여자 정보: uuid, 프로필사진, 닉네임
 */
export async function getTeamChatroomData(roomNumber: string, token: string) {
  // 방 참여자 목록
  const member = await getTeamRoomMember(roomNumber, token)

  if (!member || member.length === 0) {
    return []
  }

  // 방 참여자의 프로필사진, 닉네임
  const chatMemberInfos = await Promise.all(
    member.map(async (m: any) => {
      const memberInfo = await getBasicUserData(m.memberUuid, token)
      return {
        ...m,
        ...memberInfo,
      }
    }),
  )
  return chatMemberInfos
}
