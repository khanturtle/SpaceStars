'use client'

// pages/auth/popup.js
import { useEffect } from 'react'

import { signIn } from 'next-auth/react'

const AuthPopup = () => {
  useEffect(() => {
    // NextAuth.js를 사용하여 카카오 로그인 처리
    signIn('kakao', { callbackUrl: `${window.location.origin}/auth/callback` })
  }, [])

  return <div>카카오 로그인 중...</div>
}

export default AuthPopup
