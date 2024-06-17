import { RecentMessageType } from '@/apis/chat'

export interface RoomInfoType {
  peerName: string | undefined
  peerProfileImage: string | undefined
  recentMessage: RecentMessageType | undefined
}
