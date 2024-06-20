import { getChatRooms, getRoomMember } from '@/apis/chat'
import { getBasicUserData } from './getUserData'

/** 1:1 채팅방 목록을 정보를 담아 가져오기
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
