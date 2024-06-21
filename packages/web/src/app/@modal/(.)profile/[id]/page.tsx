import { getGameById } from '@/apis/getGame'

import Modal from '@/components/modal/modal'
import ProfileLayout from '@/containers/profile-modal/profileLayout'
import { fetchLikedGames } from '@/lib/fetchGamesData'

import { getAllProfileDataByUuid } from '@/lib/getAllProfileData'
import { PlayGameType } from '@/types/type'

/** 게임 정보와 옵션을 병렬로 조회 */
async function fetchPlayGames(playGames: PlayGameType[]) {
  try {
    const results = []

    for (const item of playGames) {
      // 게임 정보 조회
      const gameInfo = await getGameById(item.gameId)

      // 옵션 정보 조회
      const optionInfos = []
      // FIXME: 이거아님
      // if (item.tierId !== null) {
      //   optionInfos.push(getGameOptionDetail(item.tierId, 'isTier'))
      // }
      // if (item.classId !== null) {
      //   optionInfos.push(getGameOptionDetail(item.classId, 'isClass'))
      // }
      // if (item.positionId !== null) {
      //   optionInfos.push(
      //     getGameOptionDetail(item.positionId, 'isPosition'),
      //   )
      // }
      // if (item.serverId !== null) {
      //   optionInfos.push(getGameOptionDetail(item.serverId, 'isServer'))
      // }

      results.push({
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

export default async function Page({ params }: { params: { id: string } }) {
  // params.id 의 uuid를 가진 회원 정보 조회
  const targetUuid = params.id
  const allProfile = await getAllProfileDataByUuid(targetUuid)

  const likedGames = allProfile.likedGameIds ?? []
  const likedGamesInfo = await fetchLikedGames(likedGames)

  // 하는 게임 정보 조회
  const playGames = allProfile.playGames ?? []
  const playGamesInfo = await fetchPlayGames(playGames)
  // console.log(playGames)

  return (
    <Modal>
      <ProfileLayout profileData={allProfile} likedGamesInfo={likedGamesInfo} />
    </Modal>
  )
}
