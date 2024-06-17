'use client'

import { usePathname } from 'next/navigation'
import { useState } from 'react'
import styles from './Sidebar.module.css'

export default function RightSidebar() {
  // 이걸 같이 관리하는게 네브바에 있어야 함. 열고 닫을 수 있게
  const [rightSide, setRightSide] = useState(false)
  const pathName = usePathname()

  // console.log(pathName)
  if (pathName === '/dashboard/chat') {
    return <section className={styles.rightSidebar}>채팅 참여자</section>
  }
  return (
    <section
      className={`${styles['right-side']} ${rightSide && `${styles.active}`}`}
    >
      <div className={styles['side-wrapper']}>
        <div className={styles['side-title']}>Friends</div>
        <div className={styles.user}>
          <img
            src="https://pbs.twimg.com/profile_images/1102351320567164931/ZCkJgJIH.png"
            alt=""
            className={styles['user-img']}
          />
          <div className={styles.username}>
            Lisandro Matos
            <div className={styles.date}>12 hours ago</div>
          </div>
        </div>

        <div className={styles.contacts}>
          <div className={styles.user}>
            <img
              src="https://pbs.twimg.com/profile_images/1102351320567164931/ZCkJgJIH.png"
              alt=""
              className={styles['user-img']}
            />
            <div className={styles.username}>
              Lisandro Matos
              <div className={`${styles['user-status']} ${styles.idle}`}></div>
            </div>
          </div>
        </div>
      </div>
    </section>
  )
}
