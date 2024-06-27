'use client'

import { useRouter } from 'next/navigation'

import styles from './navbar.module.css'

export const LoginButton = () => {
  const router = useRouter()

  return (
    // TODO: 버튼 커스텀
    <button
      type="button"
      onClick={() => router.push('/sign-in')}
      className={styles['login-button']}
    >
      로그인 | 회원가입
    </button>
  )
}
