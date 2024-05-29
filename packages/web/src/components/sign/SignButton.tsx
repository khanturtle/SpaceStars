'use client'

import { useRouter } from 'next/navigation'

import { KakaoButton } from '@packages/ui'

const Button = ({ type }: { type: 'sign-in' | 'sign-up' }) => {
  const typeLabel = type === 'sign-in' ? '카카오 로그인' : '카카오 회원가입'
  const router = useRouter()

  // eslint-disable-next-line @typescript-eslint/no-shadow
  const handleClick = (type: string) => {
    // 카카오 로그인/회원가입 API 호출
    if (type === 'sign-up') {
      // 카카오 회원가입 API 호출
      router.replace('/additional-info')
    }
  }

  return <KakaoButton label={typeLabel} onClick={() => handleClick(type)} />
}

export default Button
