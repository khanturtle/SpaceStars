'use client'

import { useState } from 'react'

import FrontCard from '@/components/Card/FrontCard'

import BackCard from './BackCard'

import styles from './swipe.module.css'

interface SwipeCardProps {
  item: any
  MBTIName: string | null
  playGames: any
  hoveringIndex: number
  index: number
  onMouseEnter: () => void
  onMouseLeave: () => void
}

const SwipeCard = ({
  item,
  playGames,
  MBTIName,
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
          <FrontCard item={item} MBTIName={MBTIName} />
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
  MBTINames,
}: {
  profileDataList: any[]
  playGames: any
  MBTINames: any
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
              key={index}
              item={item}
              playGames={playGames[index] ?? []}
              MBTIName={MBTINames[index]}
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
