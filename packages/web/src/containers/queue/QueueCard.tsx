import { GameIcon4 } from '@packages/ui'

import FrontCard from '@/components/Card/FrontCard'

import styles from './queue.module.css'

const MatchingCard = () => {
  return (
    <div className={styles['matching-card']}>
      <div className={styles['matching-wrapper']}>
        <GameIcon4 fill="#fff" />
        <p className={styles['matching-text']}>matching...</p>
      </div>
    </div>
  )
}

const QueueFrontCard = ({
  myData,
  mbtiName,
}: {
  myData: any
  mbtiName: string
}) => {
  return (
    <div className={styles['front-wrapper']}>
      <FrontCard item={myData} MBTIName={mbtiName} />
    </div>
  )
}

export { MatchingCard, QueueFrontCard }
