import Link from 'next/link'

import { LogoIcon, LogoName } from '@packages/ui'

import styles from './Navbar.module.css'
import { LoginButton } from './NavbarItem'

const NavRightItem = ({ children }: { children?: React.ReactNode }) => (
  <div className="flex max-w-[328px] pl-[50px]">{children}</div>
)

const NavMidItem = ({
  children,
  className,
}: {
  children?: React.ReactNode
  className?: string
}) => <div className={`${styles.mid} ${className}`}>{children}</div>

export default function Navbar({ children }: { children?: React.ReactNode }) {
  return (
    <nav className={`${styles.nav}`}>
      <div className="w-[260px]">
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

Navbar.RightItem = NavRightItem
Navbar.MidItem = NavMidItem
Navbar.LoginButton = LoginButton
