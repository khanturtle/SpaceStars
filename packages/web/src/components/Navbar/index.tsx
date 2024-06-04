'use client'

import { useRouter } from 'next/navigation'

import { useSession } from 'next-auth/react'

import styles from './Navbar.module.css'

export default function Navbar() {
  const router = useRouter()
  const { data: session, status, update } = useSession()
  console.log(session)

  const handleClick = () => {
    // 예를 들어 여기서 회원 프로필 정보를 받아와서 세션에 저장한다.
    update({
      data: {
        nickname: 'test',
      },
    })
  }
  const loginTest = async () => {
    try {
      const res = await fetch(
        `${process.env.NEXT_PUBLIC_API_URL_V1}/auth/login`,
        {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            email: 'lotus0028@kakao.com',
          }),
        },
      )
      if (!res) {
        throw new Error()
      }
      console.log(res.json())
    } catch (e) {
      console.log(e)
    }
  }

  const gameTest = async () => {
    try {
      const res = await fetch(`${process.env.NEXT_PUBLIC_API_URL_V1}/game`)
      if (res) {
        console.log(res.json())
      }
    } catch (e) {
      console.log(e)
    }
  }

  return (
    <nav className={styles.nav}>
      {status === 'authenticated' && (
        <button type="button" onClick={handleClick}>
          세션 업데이트 테스트!
        </button>
      )}
      <button
        className="p-2 border border-black border-solid"
        type="button"
        onClick={loginTest}
      >
        로그인 테스트
      </button>
      <button
        className="p-2 border border-black border-solid"
        type="button"
        onClick={gameTest}
      >
        게임 테스트
      </button>
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
