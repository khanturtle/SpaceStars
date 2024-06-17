'use client'

import Image from 'next/image'
import { usePathname } from 'next/navigation'

import { useState } from 'react'

import styles from './Sidebar.module.css'

const MemberItem = () => {
  const status = 'offline'

  return (
    <div className={styles.contacts}>
      <div className={styles.user}>
        <div className={`${styles['user-img']} ${styles[status]}`}>
          <Image src="" alt="" fill />
        </div>

        <div className={styles.username}>
          <p>Lisandro MatoLisandro MatoLisandro Mato</p>
          <div className={`${styles['user-status']} ${styles[status]}`}></div>
        </div>
      </div>
    </div>
  )
}

export default function RightSidebar() {
  // FIXME: 이걸 같이 관리하는게 네브바에 있어야 함. 열고 닫을 수 있게
  const [rightSide, setRightSide] = useState(false)
  const pathName = usePathname()
  const pathParts = pathName.split('/')

  // 채팅방
  if (pathName.includes('chat') && pathParts.length === 4) {
    return (
      <section
        className={`${styles['right-side']} ${rightSide && `${styles.active}`}`}
      >
        채팅 참여자
      </section>
    )
  }
  // TODO: 친구 리스트 불러오기

  // 기본 친구
  return (
    <section
      className={`${styles['right-side']} ${rightSide && `${styles.active}`}`}
    >
      <div className={styles['side-wrapper']}>
        <div className={styles['side-title']}>Friends</div>

        <MemberItem />
      </div>
    </section>
  )
}
