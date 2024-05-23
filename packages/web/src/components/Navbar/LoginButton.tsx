'use client'

import { signIn, signOut, useSession } from 'next-auth/react'

type LoginButtonProps = {
  styles: { [key: string]: string }
}

function LoginButton({ styles }: LoginButtonProps) {
  const { data } = useSession()

  console.log('data', data)

  const handleClick = async () => {
    await signIn('kakao')
  }

  if (data?.user) {
    return (
      <>
        <div>아바타</div>
        {/* eslint-disable-next-line react/button-has-type */}
        <button onClick={() => signOut()}>로그아웃</button>
      </>
    )
  }

  return (
    <div className={styles.nav_login}>
      {/* eslint-disable-next-line react/button-has-type */}
      <button onClick={handleClick}>로그인</button>
    </div>
  )
}

export default LoginButton
