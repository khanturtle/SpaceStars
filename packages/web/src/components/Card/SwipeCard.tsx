import styles from './card.module.css'

const SwipeCard = ({ children }: { children?: React.ReactNode }) => {
  return (
    <div className={styles['flip-card']}>
      <div className={styles['flip-card-inner']}>{children}</div>
    </div>
  )
}

/** 앞면 데이터:
 * 프로필 사진, 닉네임, 성별, 나이, 좋아하는게임, 내가하는게임, MBTI, 게임성향테스트 결과 */
const Front = () => {
  return <div className={styles['flip-card-front']}>앞</div>
}

/** 뒷면 데이터:
 * 프로필 사진, 닉네임, 한줄메시지, 레벨, 대표게임, 내가 하는 게임 */
const Back = () => {
  return <div className={styles['flip-card-back']}>뒤</div>
}

SwipeCard.Front = Front
SwipeCard.Back = Back

export default SwipeCard
