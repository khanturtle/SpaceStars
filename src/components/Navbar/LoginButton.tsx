'use client'

import { signIn, signOut, useSession } from 'next-auth/react'

type LoginButtonProps = {
  styles: { [key: string]: string }
}

const LoginButton = ({ styles }: LoginButtonProps) => {
  const { data } = useSession()

  console.log('data', data)

  const handleClick = async () => {
    await signIn('kakao')
  }

  if (data?.user) {
    return (
      <>
        <div>아바타</div>
        <button onClick={() => signOut()}>로그아웃</button>
      </>
    )
  }

  return (
    <div className={styles.nav_login}>
      <button onClick={handleClick}>로그인</button>
    </div>
  )
}

export default LoginButton
