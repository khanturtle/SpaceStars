'use client'

import { getIsProfile } from '@/apis/member'
import { useSession } from 'next-auth/react'

export default function TmpButton() {
  const { data: session, status } = useSession()
  const token = (session?.user?.data.accessToken as string) || ''

  const handleClick = () => {
    console.log(session?.user?.data?.accessToken)
    if (status === 'authenticated') {
      getIsProfile(token)
    }
  }

  return (
    <>
      {status === 'authenticated' && (
        <button type="button" onClick={handleClick}>
          프로필 유무 확인
        </button>
      )}
    </>
  )
}
