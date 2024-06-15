import { GameOptionType } from '@/store/gameStore'
// TODO: 서버액션 테스트
const BASE_URL = process.env.NEXT_PUBLIC_API_URL_V1

// 1. 좋아하는 게임 입력
async function updateLikeGame(gameIds: number[], token: string) {
  await fetch(`${BASE_URL}/profile/liked-game`, {
    method: 'PATCH',
    headers: {
      'Content-Type': 'application/json',
      Authorization: token,
    },
    body: JSON.stringify({
      likedGameIdList: gameIds,
    }),
  })
}

// 2. 대표 게임 입력
async function updateMainGame(gameWithOption: GameOptionType, token: string) {
  await fetch(`${BASE_URL}/profile/play-game`, {
    method: 'PATCH',
    headers: {
      'Content-Type': 'application/json',
      Authorization: token,
    },
    body: JSON.stringify([
      {
        ...gameWithOption,
      },
    ]),
  })
}

// 3. MBTI 입력
async function updateMbti(mbtiId: number, token: string) {
  await fetch(`${BASE_URL}/profile/info`, {
    method: 'PATCH',
    headers: {
      'Content-Type': 'application/json',
      Authorization: token,
    },
    body: JSON.stringify({
      mbtiId,
    }),
  })
}

export async function updateProfile(
  gameIds: number[],
  gameWithOption: GameOptionType,
  mbtiId: number,
  token: string,
) {
  try {
    Promise.all([
      updateLikeGame(gameIds, token),
      updateMainGame(gameWithOption, token),
      updateMbti(mbtiId, token),
    ])
  } catch (err) {
    console.error('Error updating user profile:', err)
  }
}
