export interface ApiResponseType {
  message: string
  code: number
}

export interface ApiTokenErrorType {
  error: string
}

// --game
export interface GameType {
  gameId: number
  gameNameKor: string
  gameName: string
  gameImage: string
  gameLogoImage: string
}
export interface GameTypes {
  index: number
  gameId: number
  gameNameKor: string
  gameName: string
  gameImage: string
  gameLogoImage: string
}
// game--

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
  gameId: number
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
export interface RecentMessageType {
  senderUuid: string
  lastChatMessage: string
  createdAt: string | null
}
export interface UnreadMessageCount {
  unReadMessageCount: number
}
// chatroom--

// --GroupChat
export interface GroupChatInfo {
  gameId: number
  index: number
  isFinished: boolean
  isPassword: boolean
  maxMembers: number
  memo: string
  password?: number
  roomName: string
  roomNumber: string
}
// GroupChat--


// alarmList
export interface AlarmListType{
  index: number;
  senderUuid: string;
  checkStatus: string;
  alarmType: string;
  content: string;
  senderNickname: string;
  senderProfileImage: string;
}