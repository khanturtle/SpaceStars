'use client'

import { useState } from 'react'

import styles from './swipe.module.css'

/** 앞면 데이터:
 * 프로필 사진, 닉네임, 성별, 나이, 좋아하는게임, 내가하는게임, MBTI, 게임성향테스트 결과 */

/** 뒷면 데이터:
 * 프로필 사진, 닉네임, 한줄메시지, 레벨, 대표게임, 내가 하는 게임 */

interface SwipeCardProps {
  hoveringIndex: number
  index: number
  onMouseEnter: () => void
  onMouseLeave: () => void
}

const SwipeCard = ({
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
          뒤
        </div>
      </div>
    </div>
  )
}

export default function SwipeCardWrapper() {
  const [hoveringIndex, setHoveringIndex] = useState(-1)

  // TODO: 스와이프 4개 프랍받아 사용
  const data = [
    {
      index: 1,
    },
    {
      index: 2,
    },
    {
      index: 3,
    },
    {
      index: 4,
    },
  ]

  const handleMouseEnter = (index) => {
    setHoveringIndex(index)
  }

  const handleMouseLeave = () => {
    setHoveringIndex(-1)
  }
  return (
    <section className={styles['card-wrapper']}>
      <div className={styles.cards}>
        {data &&
          data.map((item, index) => (
            <SwipeCard
              key={index}
              hoveringIndex={hoveringIndex}
              index={index}
              onMouseEnter={() => handleMouseEnter(index)}
              onMouseLeave={handleMouseLeave}
            />
          ))}
      </div>

      <div className={styles.indicator}>
        {data.map((_, index) => (
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
