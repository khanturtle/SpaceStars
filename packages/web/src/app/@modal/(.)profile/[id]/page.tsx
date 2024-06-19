import { getProfileImagesByUuid } from '@/apis/getProfileImage'
import Modal from '@/components/modal/modal'
import ProfileLayout from '@/containers/profile-modal/profileLayout'

export default async function Page({ params }: { params: { id: string } }) {
  // params.id 의 uuid를 가진 회원 정보 조회
  console.log(params.id)
  return (
    <Modal>
      <ProfileLayout />
    </Modal>
  )
}
