'use client'

import Image from 'next/image'

import { useContext } from 'react'

import {
  GameIcon,
  GameIcon2,
  GameIcon3,
  TeamCardJoinButton,
} from '@packages/ui'

import { calculateLevel } from '@/lib/calculateLevel'

import FormLayout from '@/components/form/formLayout'
import { ModalContext } from '@/components/providers/modal-provider'

import SwipeForm from './SwipeForm'
import styles from './swipe.module.css'

const Desc = ({
  icon,
  title,
  type = 'intro',
  description,
  game,
  games,
}: {
  icon: React.ReactNode
  title: string
  type?: 'intro' | 'games' | 'main'
  description?: string
  game?: any
  games?: any[]
}) => {
  return (
    <div className={styles['desc-wrapper']}>
      <p className="flex items-center gap-1">
        <i>{icon}</i>
        <span className={styles['desc-title']}>{title}</span>
      </p>

      {type === 'intro' && (
        <p className={styles['desc-content']}>{description}</p>
      )}

      {type === 'main' && (
        <div className={styles['desc-content']}>
          <p>{game.gameInfo.gameNameKor ?? ''}</p>
          {game.optionInfo.length > 0 && (
            <p className={styles['main-option']}>
              {game.optionInfo.map((option: any, index: number) => (
                <span key={index}>{option.nameKor ?? ''}</span>
              ))}
            </p>
          )}
        </div>
      )}

      {type === 'games' && (
        <div className={styles['desc-content']}>
          <p className={styles['main-option']}>
            {games?.map((game, index) => (
              <span key={index}>{game.gameInfo.gameNameKor ?? ''}</span>
            ))}
          </p>
        </div>
      )}
    </div>
  )
}

export default function BackCard({
  item,
  playGames,
}: {
  item: any
  playGames: any
}) {
  const level = calculateLevel(item.profileInfo.exp ?? 0)

  // playGames에서 main: true
  const mainGame = playGames.find((game: any) => game.main)

  const { openModal } = useContext(ModalContext)

  /** 매칭 요청 */
  const handleClick = () => {
    openModal(
      <div className={`relative h-full flex flex-col items-center`}>
        <FormLayout
          className="relative h-full px-[204px] pt-[90px] pb-[85px]
                  flex flex-col items-center"
        >
          <FormLayout.Legend
            title="Matching Request"
            description={`요청 메시지를 입력해주세요.`}
          />
          <SwipeForm uuid={item.uuid} />
        </FormLayout>
      </div>,
    )
  }

  return (
    <div className={styles.back}>
      <div className={styles.profile}>
        <Image
          className={styles.image}
          src={item.mainProfileImage}
          alt={item.authProfile.nickname ?? ''}
          width={55}
          height={55}
        />
        <div className={styles.nickname}>
          <p className={styles.level}>Lv. {level}</p>
          <p className={styles.name}>{item.authProfile.nickname ?? ''}</p>
        </div>
      </div>

      <div className={styles['desc-all']}>
        {item.profileInfo.introduction && (
          <Desc
            icon={<GameIcon />}
            title="한줄소개"
            description={item.profileInfo.introduction}
          />
        )}
        {/* 대표게임 */}
        {item.playGames.length > 0 && (
          <Desc
            icon={<GameIcon2 />}
            title="대표게임"
            type="main"
            game={mainGame}
          />
        )}
        {/* 플레이하는 게임 */}
        {item.playGames.length > 0 && (
          <Desc
            icon={<GameIcon3 />}
            title="게임"
            type="games"
            games={playGames}
          />
        )}
      </div>

      <div className={styles['button-wrapper']}>
        <TeamCardJoinButton buttonText="매칭 요청하기" onClick={handleClick} />
      </div>
    </div>
  )
}
