import Link from 'next/link'

import TmpButton from '@/containers/additional-details/TmpButton'
import { DevModalOpenBtn } from '@/containers/additional-details/AdditionalDetailsLayout'

export default async function Page() {
  return (
    <main>
      <h1>컴포넌트dd</h1>
      <Link href="test">테스트 페이지 ㄱㄱ</Link> |
      <Link href="additional-info">모달얍</Link> |
      <Link href="additional-details?step=1">모달얍2</Link> |
      <TmpButton /> |
      <DevModalOpenBtn />
    </main>
  )
}
