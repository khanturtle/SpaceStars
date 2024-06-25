import styles from './queue.module.css'
import { MatchingCard } from '@/containers/queue/QueueCard'

const QueueLoader = ({ isMatching }: { isMatching: boolean }) => {
  const color = isMatching ? '#FFFFFF' : '#FF5DBF'

  return (
    <div className="absolute -translate-y-2/4 top-2/4">
      <div className="la-ball-pulse" style={{ color: color }}>
        <div></div>
        <div></div>
        <div></div>
        <div></div>
      </div>
    </div>
  )
}

export default function QueueCardWrapper({
  children,
}: {
  children?: React.ReactNode
}) {
  return <section className={styles['queue-cards']}>{children}</section>
}

QueueCardWrapper.Loader = QueueLoader
QueueCardWrapper.MatchingCard = MatchingCard
