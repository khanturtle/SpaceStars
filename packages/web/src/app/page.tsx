import Link from 'next/link'

import { Avatar } from '@packages/ui'
import { getServerSession } from 'next-auth/next'

import { options } from './api/auth/[...nextauth]/options'

import Navbar from '@/components/Navbar/Navbar'

export default async function Page() {
  const session = await getServerSession(options)
  const profileImage =
    session?.user?.picture ||
    'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAoAAAAKCAYAAACNMs+9AAAAFklEQVR42mN8//HLfwYiAOOoQvoqBABbWyZJf74GZgAAAABJRU5ErkJggg=='

  return (
    <>
      <Navbar>
        <Navbar.MidItem className="flex-1">
          <div className="flex-1" />
        </Navbar.MidItem>

        <Navbar.RightItem>
          {session?.user?.data ? (
            <Avatar image_url={profileImage} />
          ) : (
            <Navbar.LoginButton />
          )}
        </Navbar.RightItem>
      </Navbar>

      <main>
        <h1>컴포넌트dd</h1>
        <Link href="test">테스트 페이지 ㄱㄱ</Link> |
        <Link href="additional-info">모달얍</Link> |
        <Link href="additional-details?step=1">모달얍2</Link>
      </main>
    </>
  )
}
