'use client'

import { KakaoButton } from '@packages/ui'
import { signIn } from 'next-auth/react'

// FIXME: 일단 안됨
// eslint-disable-next-line @typescript-eslint/no-unused-vars, no-unused-vars
const openAuthPopup = () => {
  const width = 500
  const height = 600
  const left = (window.innerWidth - width) / 2
  const top = (window.innerHeight - height) / 2
  // eslint-disable-next-line @typescript-eslint/no-unused-vars, no-unused-vars
  const popup = window.open(
    '/auth/popup',
    'authPopup',
    `width=${width},height=${height},top=${top},left=${left}`,
  )

  // 메시지 수신 대기
  window.addEventListener('message', (event) => {
    if (event.origin === window.location.origin) {
      console.log('인증 성공:', event.data)
      // 인증 성공 후 처리 로직
    }
  })
}

const Button = ({ label = '' }: { label: string }) => {
  const handleClick = () => {
    signIn('kakao', {
      redirect: false,
      callbackUrl: '/dashboard',
    })
  }

  return (
    <KakaoButton
      className="mb-[37px]"
      label={label}
      onClick={handleClick}
      // onClick={openAuthPopup}
    />
  )
}

export default Button
