'use client'

import { useRouter } from 'next/navigation'

import { Avatar, LogoIcon, LogoName } from '@packages/ui'
import { useSession } from 'next-auth/react'

import styles from './Navbar.module.css'

const Logo = () => {
  return (
    <div className={styles.nav_logo}>
      <h1 className="hidden">SPACE STAR</h1>
      <LogoIcon width="40" height="34" />
      <LogoName width="120" />
    </div>
  )
}

export default function Navbar({ isLogo = false }: { isLogo?: boolean }) {
  const router = useRouter()
  const { data: session, status } = useSession()

  return (
    <nav className={`${styles.nav}`}>
      {isLogo && <Logo />}

      <div className="flex-1" />

      {/* <div className={styles.nav_mode}>모드</div> */}

      {status === 'authenticated' && <Avatar />}
      {status === 'unauthenticated' && (
        <button type="button" onClick={() => router.push('/sign-in')}>
          로그인 | 회원가입
        </button>
      )}
    </nav>
  )
}

Navbar.Logo = Logo
