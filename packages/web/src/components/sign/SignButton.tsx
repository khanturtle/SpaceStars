'use client'

import { useRouter } from 'next/navigation'

import { useEffect, useState } from 'react'

import { KakaoButton } from '@packages/ui'
import { signIn } from 'next-auth/react'

const Button = ({ type }: { type: 'sign-in' | 'sign-up' }) => {
  const typeLabel = type === 'sign-in' ? '카카오 로그인' : '카카오 회원가입'
  const router = useRouter()

  const [providers, setProviders] = useState(null)

  useEffect(() => {
    const handleMessage = async (event) => {
      if (event.origin !== window.location.origin) return

      if (event.data.type === 'kakao-login') {
        // 메시지로 전달된 URL을 사용하여 로그인 상태 갱신
        await signIn('kakao', {
          callbackUrl: event.data.url,
          redirect: false,
        })
      }
    }

    window.addEventListener('message', handleMessage)

    return () => {
      window.removeEventListener('message', handleMessage)
    }
  }, [])

  // useEffect(() => {
  //   ;(async () => {
  //     const res: any = await getProviders()
  //     console.log(res)
  //     setProviders(res)
  //   })()
  // }, [])

  // const handleKakao = async () => {
  //   const result = await signIn('kakao', {
  //     redirect: true,
  //     callbackUrl: '/',
  //   })
  // }

  function openKakaoPopup() {
    const clientId = process.env.NEXT_PUBLIC_KAKAO_CLIENT_ID
    const redirectUri = `${window.location.origin}/api/auth/callback/kakao`
    const kakaoLoginUrl = `https://kauth.kakao.com/oauth/authorize?client_id=${clientId}&redirect_uri=${redirectUri}&response_type=code`

    const popupWidth = 600
    const popupHeight = 800
    const popupLeft = window.screen.width / 2 - popupWidth / 2
    const popupTop = window.screen.height / 2 - popupHeight / 2

    const popup = window.open(
      kakaoLoginUrl,
      'kakaoLoginPopup',
      `width=${popupWidth},height=${popupHeight},left=${popupLeft},top=${popupTop}`,
    )
  }

  // useEffect(() => {
  //   const handleMessage = (event) => {
  //     if (
  //       event.origin === window.location.origin &&
  //       event.data === 'kakaoLoginSuccess'
  //     ) {
  //       // 팝업 창이 닫히고 회원가입 로직 진행
  //       console.log('카카오 로그인이 성공적으로 완료되었습니다.')
  //       // 회원가입 로직 추가
  //       router.push('/additional-info')
  //     }
  //   }

  //   window.addEventListener('message', handleMessage)

  //   return () => {
  //     window.removeEventListener('message', handleMessage)
  //   }
  // }, [])

  const handleClick = (type: string) => {
    openKakaoPopup()
    // 카카오 로그인/회원가입 API 호출

    // signIn('kakao', { callbackUrl: '/' })
    // signIn('kakao', { redirect: true, callbackUrl: '/' })

    // if (type === 'sign-up') {
    //   // 카카오 회원가입 API 호출
    //   router.replace('/additional-info')
    // }
  }

  return (
    <KakaoButton
      className="mb-[37px]"
      label={typeLabel}
      onClick={() => handleClick(type)}
    />
  )
}

export default Button
