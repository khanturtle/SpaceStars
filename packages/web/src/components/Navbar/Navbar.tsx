'use client'

import Link from 'next/link'
import { usePathname } from 'next/navigation'

import { Session } from 'next-auth'

import { Avatar, LogoIcon, LogoName } from '@packages/ui'

import { defaultImage } from '@/store/defaultState'

import { LoginButton, ThemeButton } from './NavbarItem'

import Gutter from '../Gutter'
import SearchBox from '../search/SearchBox'
import { Alarm } from './Alarm'
import TitleHeader from './TitleHeader'

const NavRightBox = ({ children }: { children?: React.ReactNode }) => {
  return <div className="flex max-w-[328px] pl-[50px]">{children}</div>
}

export default function Navbar({
  session,
  profileImageUrl,
}: {
  session: Session | null
  profileImageUrl: string | null | undefined
}) {
  const pathName = usePathname()
  const isChatPath = pathName.startsWith('/dashboard/chat')
  const noSearchBoxPage = [
    '/dashboard/swipe',
    '/dashboard/queue',
    '/dashboard/queue/start',
  ]

  return (
    <header className="h-[100px] nav sticky top-0 z-[1000]">
      <nav className="w-full flex flex-row items-center sticky px-[54px] py-5  left-0 top-0">
        {/* Left */}
        <div className="w-[210px]">
          <Link
            href="/"
            className="w-[120px] h-full flex flex-col content-start justify-center"
          >
            <h1 className="text-[0px]">SPACE STAR</h1>
            <div id="logo">
              <LogoIcon width="40" height="34" />
              <LogoName width="120" />
            </div>
          </Link>
        </div>

        {!noSearchBoxPage.includes(pathName) &&
          !isChatPath &&
          !pathName.includes('friends-list') && (
            <>
              <SearchBox />
              <Gutter className="flex-1" />
            </>
          )}

        {isChatPath && (
          <>
            <TitleHeader title="Chat" type="CHAT" />
            <Gutter className="flex-1" />
          </>
        )}

        {pathName.includes('swipe') && (
          <TitleHeader
            title="SWIPE"
            description="게임을 같이 할 나의 친구를 찾아드려요!"
            type="DESC"
          />
        )}

        {pathName.includes('queue') && (
          <TitleHeader
            title="QUEUE"
            description="게임을 같이 할 나의 친구를 찾아드려요!"
            type="DESC"
          />
        )}

        {pathName.includes('friends-list') && (
          <>
            <TitleHeader title="Friends" type="CHAT" />
            <Gutter className="flex-1" />
          </>
        )}

        {/* Right */}
        <NavRightBox>
          <ThemeButton />
          <Alarm />

          {session?.user ? (
            <Link href="/dashboard/my-page">
              <Avatar image_url={profileImageUrl ?? defaultImage} />
            </Link>
          ) : (
            <LoginButton />
          )}
        </NavRightBox>
      </nav>
    </header>
  )
}
