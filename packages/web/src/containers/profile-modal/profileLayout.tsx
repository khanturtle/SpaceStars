import { GameType } from '@/types/type'

import CreateChatButton from './createChatButton'
import ProfileContainer from './profileContainer'

const ProfileButtons = ({ uuid, type }: { uuid: string; type: string }) => {
  if (type === 'FRIEND') {
    return (
      <div>
        친구 삭제 버튼
        <CreateChatButton uuid={uuid} />
      </div>
    )
  }
  return <div>ㅇㅅㅇ</div>
}

export default function ProfileLayout({
  profileData,
  likedGamesInfo,
  playGamesInfo,
}: {
  profileData: any
  likedGamesInfo: (GameType | null)[]
  playGamesInfo: any[]
}) {
  return (
    <section className="relative h-full flex flex-col items-center px-[204px] py-[120px]">
      <ProfileContainer
        profileData={profileData}
        likedGamesInfo={likedGamesInfo}
        playGamesInfo={playGamesInfo}
      />

      <ProfileButtons type={profileData.friendType} uuid={profileData.uuid} />
    </section>
  )
}
