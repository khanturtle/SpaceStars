import { calculateAge } from '@/lib/calculateAge'
import Image from 'next/image'

import styles from './profile.module.css'

export default function ProfileContainer({
  profileData,
}: {
  profileData: any
}) {
  const age = calculateAge(profileData.authProfile.birth)

  // 게임 성향 ID -> 게임 성향 결과 받아오기. 없으면, ㄴㄴ
  const gamePreference = profileData.profileInfo.gamePreferenceId
  // mbtiID -> MBTI 이름 받아오기
  const mbti = profileData.profileInfo.mbtiId

  // 경험치 -> 올바른 레벨, 남은 경험치 받아오기
  const exp = profileData.profileInfo.exp

  // 플레이하는 게임 -> ID로 게임 이름 받아오기
  const palyGames = profileData.playGames

  return (
    <section className="bg-[red]">
      <div className={`relative w-12 h-12 ${styles.image}`}>
        <Image
          src={profileData.mainProfileImage}
          alt={profileData.authProfile.nickname}
          fill
        />
      </div>
      <div>
        <p>{profileData.authProfile.nickname}</p>
        <p>만 {age} 세</p>
        <p>{profileData.authProfile.gender}</p>
      </div>
      <div>
        <p>한 줄 소개: {profileData.profileInfo.introduction}</p>
        <p>게임 성향: {profileData.profileInfo.gamePreferenceId}</p>
        <p>MBTI: {profileData.profileInfo.mbtiId}</p>
        <p>경험치: {profileData.profileInfo.exp}</p>
      </div>
      <div>
        <p>플레이하는 겜쓰 </p>
        <p>좋아하는 겜쓰 </p>
      </div>
    </section>
  )
}
