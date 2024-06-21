import Modal from '@/components/modal/modal'
import ProfileLayout from '@/containers/profile-modal/profileLayout'
import { fetchLikedGames } from '@/lib/fetchGamesData'
import { getAllProfileDataByUuid } from '@/lib/getAllProfileData'

// TODO: 모달로 이동
export default async function Page({ params }: { params: { id: string } }) {
  // params.id 의 uuid를 가진 회원 정보 조회
  const targetUuid = params.id
  const allProfile = await getAllProfileDataByUuid(targetUuid)
  // console.log(allProfile)

  const likedGames = allProfile.likedGameIds ?? []
  const likedGamesInfo = await fetchLikedGames(likedGames)
  console.log(likedGamesInfo)

  return (
    <Modal>
      <ProfileLayout profileData={allProfile} likedGamesInfo={likedGamesInfo} />
    </Modal>
  )
}
