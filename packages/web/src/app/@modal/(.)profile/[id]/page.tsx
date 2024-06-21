import { getGameById } from '@/apis/getGame'
import Modal from '@/components/modal/modal'
import ProfileLayout from '@/containers/profile-modal/profileLayout'
import { getAllProfileDataByUuid } from '@/lib/getAllProfileData'

/** 게임 정보를 병렬로 조회 */
async function fetchLikedGames(likedGames: number[]) {
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

export default async function Page({ params }: { params: { id: string } }) {
  // params.id 의 uuid를 가진 회원 정보 조회
  const targetUuid = params.id
  const allProfile = await getAllProfileDataByUuid(targetUuid)

  const likedGames = allProfile.likedGameIds ?? []
  const likedGamesInfo = await fetchLikedGames(likedGames)

  // 하는 게임 정보 조회
  console.log(allProfile.playGames)

  return (
    <Modal>
      <ProfileLayout profileData={allProfile} likedGamesInfo={likedGamesInfo} />
    </Modal>
  )
}
