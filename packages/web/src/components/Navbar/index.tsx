'use client'

import { useRouter } from 'next/navigation'

import { useSession } from 'next-auth/react'

import styles from './Navbar.module.css'

export default function Navbar() {
  const router = useRouter()
  const { data: status, update } = useSession()

  // eslint-disable-next-line @typescript-eslint/no-unused-vars, no-unused-vars
  const handleClick = async () => {
    // 예를 들어 여기서 회원 프로필 정보를 받아와서 세션에 저장한다.
    await update({
      data: {
        nickname: 'testt',
      },
    })
  }

  return (
    <nav className={styles.nav}>
      <div className={styles.nav_logo}>
        <h1 className="hidden">SPACE STAR</h1>
        로고
      </div>

      <ul className={styles.nav_item}>
        <li>1</li>
        <li>2</li>
        <li>3</li>
      </ul>

      <div className="flex-1" />

      <div className={styles.nav_mode}>모드</div>

      {status === 'authenticated' ? (
        <div>아바타</div>
      ) : (
        <button type="button" onClick={() => router.push('/sign-in')}>
          로그인/회원가입
        </button>
      )}
    </nav>
  )
}
