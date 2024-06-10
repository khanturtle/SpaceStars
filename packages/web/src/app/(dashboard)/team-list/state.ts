export interface TeamType {
  index: number
  gameName: string
  roomName: string
  memo: string
  isFinished: boolean
  isPassword: boolean
  maxLimit: number
  memberCount: number
}

const Teams = [
  {
    index: 0,
    gameName: 'LEAGUE OF LEGENDS',
    roomName: '같이 겜 하실 분 구함',
    memo: '티어 골드이상만. 챌린저 쌉가능? 야스오 하지마라.야스오 하지마라.야스오 하지마라.',
    isFinished: false,
    isPassword: false,
    maxLimit: 5,
    memberCount: 1,
  },
  {
    index: 1,
    gameName: 'LEAGUE OF LEGENDS',
    roomName: '같이 겜 하실 분 구함',
    memo: '티어 골드이상만. 챌린저 쌉가능? 야스오 하지마라.',
    isFinished: true,
    isPassword: false,
    maxLimit: 5,
    memberCount: 2,
  },
  {
    index: 2,
    gameName: 'LEAGUE OF LEGENDS',
    roomName: '같이 겜 하실 분 구함',
    memo: '티어 골드이상만. 챌린저 쌉가능? 야스오 하지마라.',
    isFinished: false,
    isPassword: true,
    maxLimit: 3,
    memberCount: 3,
  },
  {
    index: 3,
    gameName: 'LEAGUE OF LEGENDS',
    roomName: '같이 겜 하실 분 구함',
    memo: '티어 골드이상만. 챌린저 쌉가능? 야스오 하지마라.',
    isFinished: true,
    isPassword: true,
    maxLimit: 5,
    memberCount: 4,
  },
  {
    index: 4,
    gameName: 'LEAGUE OF LEGENDS',
    roomName: '같이 겜 하실 분 구함',
    memo: '티어 골드이상만. 챌린저 쌉가능? 야스오 하지마라.',
    isFinished: false,
    isPassword: true,
    maxLimit: 3,
    memberCount: 3,
  },
  {
    index: 5,
    gameName: 'LEAGUE OF LEGENDS',
    roomName: '같이 겜 하실 분 구함',
    memo: '티어 골드이상만. 챌린저 쌉가능? 야스오 하지마라.',
    isFinished: true,
    isPassword: true,
    maxLimit: 5,
    memberCount: 4,
  },
]

export default Teams
