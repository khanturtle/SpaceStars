'use client'

import { useState } from 'react'
import BackCard from './BackCard'

import styles from './swipe.module.css'

/** 앞면 데이터:
 * 프로필 사진, 닉네임, 성별, 나이, 좋아하는게임, 내가하는게임, MBTI, 게임성향테스트 결과 */

interface SwipeCardProps {
  item: any
  playGames: any
  hoveringIndex: number
  index: number
  onMouseEnter: () => void
  onMouseLeave: () => void
}

const SwipeCard = ({
  item,
  playGames,
  hoveringIndex,
  index,
  onMouseEnter,
  onMouseLeave,
}: SwipeCardProps) => {
  const [flipped, setFlipped] = useState(false)

  const handleFlip = () => {
    setFlipped((prev) => !prev)
  }

  return (
    <div
      className={`${styles['flip-card']} ${
        hoveringIndex === index ? styles.hovering : ''
      }`}
      onClick={handleFlip}
      onMouseEnter={onMouseEnter}
      onMouseLeave={onMouseLeave}
    >
      <div className={styles['flip-card-inner']}>
        <div
          className={`${styles['flip-card-front']} ${flipped ? styles.flipped : ''}`}
        >
          앞
        </div>
        <div
          className={`${styles['flip-card-back']} ${flipped ? styles.flipped : ''}`}
        >
          <BackCard item={item} playGames={playGames} />
        </div>
      </div>
    </div>
  )
}

export default function SwipeCardWrapper({
  profileDataList,
  playGames,
}: {
  profileDataList: any[]
  playGames: any
}) {
  const [hoveringIndex, setHoveringIndex] = useState(-1)

  const handleMouseEnter = (index: number) => {
    setHoveringIndex(index)
  }

  const handleMouseLeave = () => {
    setHoveringIndex(-1)
  }
  return (
    <section className={styles['card-wrapper']}>
      <div className={styles.cards}>
        {profileDataList &&
          profileDataList.map((item, index) => (
            <SwipeCard
              item={item}
              playGames={playGames[index] ?? []}
              key={index}
              hoveringIndex={hoveringIndex}
              index={index}
              onMouseEnter={() => handleMouseEnter(index)}
              onMouseLeave={handleMouseLeave}
            />
          ))}
      </div>

      <div className={styles.indicator}>
        {profileDataList.map((_, index) => (
          <div
            key={index}
            className={`${styles.indicatorItem} ${
              hoveringIndex === index ? styles.active : ''
            }`}
          />
        ))}
      </div>
    </section>
  )
}
