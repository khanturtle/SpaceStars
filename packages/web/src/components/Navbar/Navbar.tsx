import Link from 'next/link'

import { LogoIcon, LogoName } from '@packages/ui'

import { LoginButton } from './NavbarItem'

const NavRightBox = ({ children }: { children?: React.ReactNode }) => (
  <div className="flex max-w-[328px] pl-[50px]">{children}</div>
)

export default function Navbar({ children }: { children?: React.ReactNode }) {
  return (
    <nav className="h-[100px] w-full flex flex-row items-center bg-[color:var(--White-50,#fff)] sticky z-[1000] px-[54px] py-5 border-b-[#ddd] border-b border-solid left-0 top-0">
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

      {children}
    </nav>
  )
}

Navbar.RightBox = NavRightBox
Navbar.LoginButton = LoginButton
