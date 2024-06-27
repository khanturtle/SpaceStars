'use client'

import { useRouter } from 'next/navigation'

import { useEffect, useState } from 'react'

import { ThemeIcon } from '@packages/ui'

import styles from './navbar.module.css'

export const LoginButton = () => {
  const router = useRouter()

  return (
    <button
      type="button"
      onClick={() => router.push('/sign-in')}
      className={styles['login-button']}
    >
      로그인 | 회원가입
    </button>
  )
}

export const ThemeButton = () => {
  const [theme, setTheme] = useState('dark')

  useEffect(() => {
    document.body.dataset.theme = theme
  }, [theme])

  const toggleTheme = () => {
    setTheme(theme === 'light' ? 'dark' : 'light')
  }

  return (
    <button type="button" onClick={toggleTheme} className="mr-8">
      <ThemeIcon
        main_fill={theme === 'light' ? 'black' : 'white'}
        sub_fill={theme === 'light' ? 'white' : 'black'}
      />
    </button>
  )
}
