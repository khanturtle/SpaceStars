import { getGameById } from '@/apis/getGame'

/** 게임 정보를 병렬로 조회 */
export async function fetchLikedGames(likedGames: number[]) {
  try {
    const gameInfoPromises = likedGames.map(getGameById)
    const gameInfos = await Promise.all(gameInfoPromises)
    // null값 제거
    return gameInfos.filter(Boolean)
  } catch (err) {
    console.error('Faild to fetchLikedGames', err)
    return []
  }
}
