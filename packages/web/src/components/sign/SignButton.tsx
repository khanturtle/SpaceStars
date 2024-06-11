'use client'

import { KakaoButton } from '@packages/ui'
import { signIn } from 'next-auth/react'

const Button = ({ label = '' }: { label: string }) => {
  const handleClick = () => {
    signIn('kakao', {
      redirect: false,
      callbackUrl: '/dashboard',
    })
  }

  return (
    <KakaoButton className="mb-[37px]" label={label} onClick={handleClick} />
  )
}

export default Button
