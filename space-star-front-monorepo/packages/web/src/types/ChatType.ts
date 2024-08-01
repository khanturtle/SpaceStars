import { RecentMessageType } from './type'

export interface RoomInfoType {
  peerName: string | undefined
  peerProfileImage: string | undefined
  recentMessage: RecentMessageType | undefined
}

export interface ChatMessageType {
  roomNumber: string
  senderUuid: string
  content: string
  messageType: 'TEXT' | 'IMAGE'
  createdAt: string
}
