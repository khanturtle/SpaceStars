import Image from 'next/image'

import styles from './swipe.module.css'

/** 뒷면 데이터:
 * 프로필 사진, 닉네임, 한줄메시지, 레벨, 대표게임, 내가 하는 게임 */

export default function BackCard({ item }: { item: any }) {
  return (
    <div className={styles.back}>
      <div className={styles.profile}>
        <Image
          className={styles.image}
          src={item.mainProfileImage}
          alt={item.authProfile.nickname ?? ''}
          width={55}
          height={55}
        />
        <div className={styles.nickname}>
          <p className={styles.level}>Lv.</p>
          <p>{item.authProfile.nickname ?? ''}</p>
        </div>
      </div>
    </div>
  )
}
