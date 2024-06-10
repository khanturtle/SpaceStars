import Link from 'next/link'

import { LogoIcon, LogoName } from '@packages/ui'

import styles from './Navbar.module.css'
import { LoginButton, MidItems } from './NavbarItem'

const NavRightBox = ({ children }: { children?: React.ReactNode }) => (
  <div className="flex max-w-[328px] pl-[50px]">{children}</div>
)

const NavMidBox = ({
  children,
  className,
}: {
  children?: React.ReactNode
  className?: string
}) => <div className={`${className}`}>{children}</div>

export default function Navbar({ children }: { children?: React.ReactNode }) {
  return (
    <nav className={`${styles.nav}`}>
      <div className="w-[210px]">
        <Link
          href="/"
          className="w-[120px] h-full flex flex-col content-start justify-center"
        >
          <h1 className="hidden">SPACE STAR</h1>
          <LogoIcon width="40" height="34" />
          <LogoName width="120" />
        </Link>
      </div>

      {children}
    </nav>
  )
}

Navbar.RightBox = NavRightBox
Navbar.MidBox = NavMidBox
Navbar.LoginButton = LoginButton
Navbar.MidItems = MidItems
