import { getIsFriend } from '@/apis/getFriends'
import { getMainProfileImageByUuid } from '@/apis/getProfileImage'
import Modal from '@/components/modal/modal'
import ProfileLayout from '@/containers/profile-modal/profileLayout'

// TODO: 모달로 이동
async function getAllProfileData(uuid: string) {
  // 대표 프로필 사진
  const mainProfileData = getMainProfileImageByUuid(uuid)

  // 친구 여부 확인
  const isFriendData = getIsFriend(uuid)

  const [result] = await Promise.all([
    Promise.all([mainProfileData, isFriendData]).then(
      ([mainProfile, isFriend]) => ({
        ...mainProfile,
        ...isFriend?.result,
      }),
    ),
  ])

  console.log(result)
}

export default async function Page({ params }: { params: { id: string } }) {
  // params.id 의 uuid를 가진 회원 정보 조회
  const targetUuid = params.id

  await getAllProfileData(targetUuid)

  return (
    <Modal>
      <ProfileLayout />
    </Modal>
  )
}
