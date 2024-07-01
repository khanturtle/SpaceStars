'use client'

import { GameIcon4 } from '@packages/ui'
import { useRouter } from 'next/navigation'

import styles from './dashboard.module.css'

const Card = () => {
  const router = useRouter()
  return (
    <section
      className={styles['queue-cards']}
      onClick={() => router.push('/dashboard/queue')}
    >
      <div className={styles['matching-card']}>
        <div className={styles['matching-wrapper']}>
          <GameIcon4 fill="#fff" />
          <p className={styles['matching-text']}>TRY MATCH</p>
        </div>
      </div>
    </section>
  )
}

export default Card
