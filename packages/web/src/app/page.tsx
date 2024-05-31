import Link from 'next/link'

import { ArrowIcon, Button, Input } from '@packages/ui'
import { getServerSession } from 'next-auth'

import Navbar from '@/components/Navbar'

async function test() {
  // const csrfToken = await getCsrfToken()
  // console.log(csrfToken)

  const session = await getServerSession()
  console.log(session)
}

export default function Page() {
  // test()

  return (
    <>
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
