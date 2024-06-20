import CreateChatButton from './createChatButton'

export default function ProfileLayout({ profileData }: { profileData: any }) {
  return (
    <section className="relative h-full flex flex-col items-center px-[204px] py-[120px]">
      <div>유저 프로필</div>
      <div>버튼: 친구요청또는삭제또는</div>
      <div>
        버튼: 채팅
        <CreateChatButton uuid={profileData.uuid} />
      </div>
    </section>
  )
}
