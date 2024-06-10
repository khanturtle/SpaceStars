'use client'

import { usePathname, useRouter } from 'next/navigation'

export const LoginButton = () => {
  const router = useRouter()

  return (
    <button type="button" onClick={() => router.push('/sign-in')}>
      로그인 | 회원가입
    </button>
  )
}

export const MidItems = () => {
  const pathname = usePathname()

  // TODO: 알맞은 컴포넌트로 바꾸기
  const PATH_NAME_KR = [
    {
      link: '/dashboard',
      name: '대시보드',
    },
    {
      link: '/dashboard/team-list',
      name: '팀원모집',
    },
    {
      link: '/dashboard/chat',
      name: 'Chat',
    },
  ]

  const currentPathName = PATH_NAME_KR.find(
    (item) => item.link === pathname,
  )?.name

  return <div>{currentPathName}</div>
}
