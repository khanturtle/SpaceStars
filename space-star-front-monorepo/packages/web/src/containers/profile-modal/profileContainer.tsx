import Image from 'next/image'

import { calculateAge } from '@/lib/calculateAge'
import { GameType } from '@/types/type'

import styles from './profile.module.css'

export default function ProfileContainer({
  profileData,
  likedGamesInfo,
  playGamesInfo,
}: {
  profileData: any
  likedGamesInfo: (GameType | null)[]
  playGamesInfo: any[]
}) {
  // console.log(profileData)
  const age = calculateAge(profileData.authProfile.birth)

  // 게임 성향 ID -> 게임 성향 결과 받아오기. 없으면, ㄴㄴ
  const gamePreference = profileData.profileInfo.gamePreferenceId
  // mbtiID -> MBTI 이름 받아오기
  const mbti = profileData.profileInfo.mbtiId

  // 경험치 -> 올바른 레벨, 남은 경험치 받아오기
  const exp = profileData.profileInfo.exp

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
        <div>
          플레이하는 겜쓰
          {playGamesInfo &&
            playGamesInfo.map((item) => (
              <div key={item.gameInfo.gameId}>
                {JSON.stringify(item.gameInfo)}

                {item.optionInfo.map((option: any) => (
                  <div key={option.id + option.name}>
                    {JSON.stringify(option)}
                  </div>
                ))}
                <hr />
              </div>
            ))}
        </div>
        <div>
          좋아하는 겜쓰
          {likedGamesInfo &&
            likedGamesInfo.map((item) => (
              <div key={'liked' + item?.gameId}>
                {item?.gameName}
                <div className="relative w-12 h-12">
                  {item && (
                    <Image src={item.gameLogoImage} alt={item.gameName} fill />
                  )}
                </div>
              </div>
            ))}
        </div>
      </div>
    </section>
  )
}
