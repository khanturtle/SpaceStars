import Modal from '@/components/modal/modal'
import ProfileLayout from '@/containers/profile-modal/profileLayout'
import { getAllProfileDataByUuid } from '@/lib/getAllProfileData'

export default async function Page({ params }: { params: { id: string } }) {
  // params.id 의 uuid를 가진 회원 정보 조회
  const targetUuid = params.id
  const allProfile = await getAllProfileDataByUuid(targetUuid)

  // console.log(allProfile)
  return (
    <Modal>
      <ProfileLayout profileData={allProfile} />
    </Modal>
  )
}
