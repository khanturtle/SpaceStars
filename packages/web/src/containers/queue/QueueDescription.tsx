import styles from './queue.module.css'

export default function QueueDescription({
  isMatching,
}: {
  isMatching: boolean
}) {
  const text = isMatching
    ? '게임 상대가 매칭 되었습니다. 게임을 요청하시겠습니까?'
    : '나의 프로필이 많이 채워질수록 더욱 나와 맞는 팀원을 찾을 수 있습니다:D'
  return (
    <div className={styles.description}>
      <p>{text}</p>
    </div>
  )
}
