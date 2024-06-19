import { getProfileImagesByUuid } from '@/apis/getProfileImage'
import Modal from '@/components/modal/modal'
import ProfileLayout from '@/containers/profile-modal/profileLayout'

// TODO: 페이지 꺼 가져와야함!
async function getAllProfileData(uuid: string) {
  // 대표 프로필 사진
  const mainProfileData = getProfileImagesByUuid(uuid)

  const [mainProfile] = await Promise.all([mainProfileData])

  console.log(mainProfile)
}

export default async function Page({ params }: { params: { id: string } }) {
  // params.id 의 uuid를 가진 회원 정보 조회

  await getAllProfileData(params.id)

  console.log(params.id)
  return (
    <Modal>
      <ProfileLayout />
    </Modal>
  )
}
