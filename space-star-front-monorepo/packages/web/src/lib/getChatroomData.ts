import {
  getChatRooms,
  getMyTeamChatRooms,
  getRoomMember,
  getTeamChatRoomsDetail,
  getTeamChatRoomsMember,
} from '@/apis/getChat'
import { getBasicUserData } from './getUserData'

/** 나의 모든 1:1 채팅방 목록을 정보를 담아 가져오기
 * 채팅방 정보: roomNumber, otherMemberUuid
 * 참여자 정보: memberUuid
 * 상대방 정보: 대표 프로필, 닉네임
 * 최근 메시지 정보:
 */
export async function getChatroomDataList() {
  const chatroomList = await getChatRooms()

  const chatroomInfo = await Promise.all(
    chatroomList.map(async (room) => {
      const { roomNumber, otherMemberUuid } = room
      // 방 참여자 목록
      const member = await getRoomMember(roomNumber)

      // otherMemberUuid의 정보: 프로필사진, 닉네임
      const otherMemberInfo = await getBasicUserData(otherMemberUuid)

      // 채팅방 정보를 구성
      return {
        ...room,
        member: member,
        otherMemberInfo,
      }
    }),
  )

  return chatroomInfo
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
  token?: string,
): Promise<MemberInfoType[]> {
  // 방 참여자 목록
  const member = await getRoomMember(roomNumber, token)

  // 방 참여자의 프로필사진, 닉네임
  const chatMemberInfos = await Promise.all(
    member.map(async (m) => {
      const memberInfo = await getBasicUserData(m.memberUuid)
      return {
        ...m,
        ...memberInfo,
      }
    }),
  )
  return chatMemberInfos
}

/** 팀 채팅방의 정보를 넣어 가져오기
 * roomNumber > 방 정보
 * 참여자 정보
 */
export async function getGroupChatroomData() {
  // 방 목록
  const roomList = await getMyTeamChatRooms()
  // 방 정보
  const chatroomInfo = await Promise.all(
    roomList.map(async (room: any) => {
      const roomInfo = await getTeamChatRoomsDetail(room.roomNumber)

      const member = await getTeamChatRoomsMember(room.roomNumber)
      const memberInfos = await Promise.all(
        member.map(async (m: any) => {
          const memberInfo = await getBasicUserData(m.memberUuid)
          return {
            ...m,
            ...memberInfo,
          }
        }),
      )
      return {
        ...room,
        roomInfo,
        memberInfos,
      }
    }),
  )

  return chatroomInfo
}
