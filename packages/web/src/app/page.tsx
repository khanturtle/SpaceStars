import Link from 'next/link'

import { ArrowIcon, Button, Input } from '@packages/ui'

import Navbar from '@/components/Navbar'

export default function Page() {
  return (
    <>
      <Navbar />
      <Button label="테ㅐ스ㅡㅌ" />
      <Input id="xptmxm" />
      <ArrowIcon />

      <main>
        <h1>컴포넌트dd</h1>
        <p>테스트</p>
        <Link href="additional-info">모달얍</Link>|
        <Link href="additional-details/0">모달얍2</Link>
      </main>
    </>
  )
}
