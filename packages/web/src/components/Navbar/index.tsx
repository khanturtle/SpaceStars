'use client'

import { useRouter } from 'next/navigation'

import { useSession } from 'next-auth/react'

import styles from './Navbar.module.css'

export default function Navbar() {
  const router = useRouter()
  const { data: session, status } = useSession()
  console.log(session, status)

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

      <button type="button" onClick={() => router.push('/sign-in')}>
        로그인/회원가입
      </button>
    </nav>
  )
}
