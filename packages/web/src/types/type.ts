export interface ApiResponseType {
  message: string
  code: number
}

export interface ApiTokenErrorType {
  error: string
}

// Profile
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
