import SwipeCard from '@/components/Card/SwipeCard'
import styles from './swipe.module.css'

export default function SwipeCardWrapper() {
  // TODO: 스와이프 5개 프랍받아 사용
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
    {
      index: 5,
    },
  ]

  return (
    <section className={styles.cards}>
      {data &&
        data.map((item) => (
          <SwipeCard>
            <SwipeCard.Front />
            <SwipeCard.Back />
          </SwipeCard>
        ))}
    </section>
  )
}
