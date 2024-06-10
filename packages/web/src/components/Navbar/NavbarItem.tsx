'use client'

import { useRouter } from 'next/navigation'

export const LoginButton = () => {
  const router = useRouter()

  return (
    <button type="button" onClick={() => router.push('/sign-in')}>
      로그인 | 회원가입
    </button>
  )
}
