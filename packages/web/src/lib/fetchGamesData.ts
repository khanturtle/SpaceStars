import { getGameById, getOptionDetail } from '@/apis/getGame'
import { PlayGameType } from '@/types/type'

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

/** 게임 정보와 옵션을 병렬로 조회 */
export async function fetchPlayGames(playGames: PlayGameType[]) {
  try {
    const results = []

    for (const item of playGames) {
      // 게임 정보 조회
      const gameInfo = await getGameById(item.gameId)

      // 옵션 정보 조회
      const optionInfos = []
      if (item.tierId !== null) {
        optionInfos.push(await getOptionDetail(item.tierId, 'isTier'))
      }
      if (item.classId !== null) {
        optionInfos.push(await getOptionDetail(item.classId, 'isClass'))
      }
      if (item.positionId !== null) {
        optionInfos.push(await getOptionDetail(item.positionId, 'isPosition'))
      }
      if (item.serverId !== null) {
        optionInfos.push(await getOptionDetail(item.serverId, 'isServer'))
      }

      results.push({
        ...item,
        gameInfo,
        optionInfo: optionInfos,
      })
    }

    return results
  } catch (error) {
    console.error('Failed to fetch game and option data: ', error)
    return []
  }
}
