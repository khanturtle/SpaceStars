import styles from './queue.module.css'

export default function QueueHeader() {
  return (
    <section className={styles.header}>
      <h3>Queue</h3>
      <p>게임을 같이 할 나의 친구를 찾아드려요!</p>
    </section>
  )
}
