export interface ApiResponseType {
  message: string
  code: number
}

export interface ApiTokenErrorType {
  error: string
}

// --auth
export interface ProfileType {
  email: string
  nickname: string
  birth: string
  gender: string
  infoAgree: boolean
  createdAt: string
  updatedAt: string
}
// auth--

// --Profile
export interface MainGameType {
  gameId: number | null
  tierId: number | null
  positionId: number | null
  classId: number | null
  serverId: number | null
  gameNickname: number | null
  main: boolean
}
export interface ProfileInfoType {
  introduction: string | null
  gamePreferenceId: number | null
  mbtiId: number | null
  exp: number
  reportCount: number
  swipe: boolean
}
export interface PlayGameType extends MainGameType {
  index: number
}
export interface LikedGameIdType {
  likedGameIdList: number[]
}
// Profile--

// --ProfileImage
export interface ProfileImageType {
  profileImageUrl: string | null
}
export interface ProfileImagesType extends ProfileImageType {
  index: number
  mainImage: boolean
}
// ProfileImage--

// --member
// member--

// --friend
export type FriendType = {
  friendType: 'FRIEND' | 'SENDER' | 'RECEIVER' | 'NONE'
}
export interface FriendsListType {
  index: number
  friendUuid: string
}
// friend--

// --chatroom
export interface ChatRoomType {
  index: number
  roomNumber: string
  otherMemberUuid: string
}
export interface ChatRoomMemberType {
  index: number
  memberUuid: string
}
// chatroom--
