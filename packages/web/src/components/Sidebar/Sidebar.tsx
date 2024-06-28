'use client'

import Link from 'next/link'
import { usePathname } from 'next/navigation'
import { useReducer } from 'react'

import {
  HeartHandshakeIcon,
  HomeIcon,
  MessagesSquareIcon,
  Radio,
  UserSearch,
} from 'lucide-react'

import styles from './Sidebar.module.css'

const SIDE_LINK = [
  {
    title: '대시보드',
    href: '/dashboard',
    svg: <HomeIcon />,
  },
  {
    title: '팀원 모집',
    href: '/dashboard/team-list',
    svg: <UserSearch />,
  },
  {
    title: '채팅',
    href: '/dashboard/chat',
    svg: <MessagesSquareIcon />,
  },
  {
    title: '추천 친구',
    href: '/dashboard/swipe',
    svg: <HeartHandshakeIcon />,
  },
  {
    title: '실시간 매칭',
    href: '/dashboard/queue',
    svg: <Radio />,
  },
]

const SideBarItem = ({ item }: { item: any }) => {
  const pathname = usePathname()

  /** 정확하게 일치하거나, /dashboard 를 제외한 경로로 시작하면 색칠 */
  const isPathname = pathname === item.href
  const isDashboardPath = '/dashboard' === item.href
  const isNotDashboardPath = '/dashboard' !== item.href
  const isChatPath = pathname.startsWith(item.href) && isNotDashboardPath

  return (
    <li className={styles['side-menu']}>
      <Link
        href={item.href}
        className={`${styles.link} ${isPathname || isChatPath ? 'text-[color:var(--sidebar-text-active)]' : isDashboardPath ? 'text-[color:var(--sidebar-text)]' : 'text-[color:var(--sidebar-text)]'}`}
      >
        {item.svg}
        <p className="text-[16px] not-italic leading-[normal]">{item.title}</p>
      </Link>
    </li>
  )
}

export default function Sidebar() {
  const [leftSide, toggleLeftSide] = useReducer((state) => {
    return !state
  }, false)

  return (
    <aside
      className={`${styles['left-side']} left-side ${leftSide ? `${styles.active}` : ''}`}
    >
      {/* 접혔을 때, 열기 버튼 */}
      <button
        className={styles['left-side-button']}
        type="button"
        onClick={toggleLeftSide}
      >
        <svg
          viewBox="0 0 24 24"
          stroke="currentColor"
          strokeWidth="2"
          fill="none"
          strokeLinecap="round"
          strokeLinejoin="round"
        >
          <line x1="3" y1="12" x2="21" y2="12"></line>
          <line x1="3" y1="6" x2="21" y2="6"></line>
          <line x1="3" y1="18" x2="21" y2="18"></line>
        </svg>
        <svg
          stroke="currentColor"
          strokeWidth="2"
          fill="none"
          strokeLinecap="round"
          strokeLinejoin="round"
          viewBox="0 0 24 24"
        >
          <path d="M19 12H5M12 19l-7-7 7-7" />
        </svg>
      </button>

      <ul className={styles['side-wrapper']}>
        {SIDE_LINK.map((item, index) => (
          <SideBarItem key={index} item={item} />
        ))}
      </ul>
    </aside>
  )
}
