import Link from 'next/link'

import { ArrowIcon, Button, Input } from '@packages/ui'
import { getServerSession } from 'next-auth'

import Navbar from '@/components/Navbar'

async function test() {
  const session = await getServerSession()
  // const session2 = await getSession
  console.log('page', session)
  console.log('===')
  return session
}

export default async function Page() {
  const session = await getServerSession()
  console.log('page', session.user)

  return (
    <>
      {session?.user.name}

      <Navbar />
      <Button label="테ㅐ스ㅡㅌ" />
      <Input id="xptmxm" />
      <ArrowIcon />

      <main>
        <h1>컴포넌트dd</h1>
        <Link href="test">테스트 페이지 ㄱㄱ</Link> |
        <Link href="additional-info">모달얍</Link> |
        <Link href="additional-details?step=1">모달얍2</Link>
      </main>
    </>
  )
}
