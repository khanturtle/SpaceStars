'use client'

import Link from 'next/link'
import { usePathname } from 'next/navigation'
import { useReducer } from 'react'

import styles from './Sidebar.module.css'

const SIDE_LINK = [
  {
    index: 1,
    title: '대시보드',
    href: '/dashboard',
  },
  {
    index: 2,
    title: '팀원 모집',
    href: '/dashboard/team-list',
  },
  {
    index: 3,
    title: '채팅',
    href: '/dashboard/chat',
  },
]

const SideBarItem = ({ item }: { item: any }) => {
  const pathname = usePathname()
  const isPathname = pathname === item.href

  return (
    <li className={styles['side-menu']}>
      <Link
        href={item.href}
        className={`${styles.a} ${isPathname ? 'text-[color:var(--Main-Color-1,#6a64e9)]' : 'text-[color:#9c9cab]'}`}
      >
        {/* TODO: 폰트 컬러, SVG 수정 */}
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          stroke="currentColor"
          strokeWidth="2"
          strokeLinecap="round"
          strokeLinejoin="round"
          viewBox="0 0 24 24"
        >
          <path d="M3 9l9-7 9 7v11a2 2 0 01-2 2H5a2 2 0 01-2-2z" />
          <path d="M9 22V12h6v10" />
        </svg>
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
      className={`${styles['left-side']} ${leftSide ? `${styles.active}` : ''}`}
    >
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
        {SIDE_LINK.map((item) => (
          <SideBarItem key={item.index} item={item} />
        ))}
      </ul>
    </aside>
  )
}
