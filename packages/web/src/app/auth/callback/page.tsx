'use client'

// pages/auth/callback.js
import { useRouter } from 'next/router'

import { useEffect } from 'react'

import { getSession } from 'next-auth/react'

const AuthCallback = () => {
  const router = useRouter()

  useEffect(() => {
    const handleAuth = async () => {
      const session = await getSession()
      if (session) {
        // 부모 창으로 메시지 전달
        window.opener.postMessage(session, window.location.origin)
        window.close()
      } else {
        // 인증 실패 시 처리 로직
        router.push('/')
      }
    }

    handleAuth()
  }, [router])

  return <div>인증 처리 중...</div>
}

export default AuthCallback
