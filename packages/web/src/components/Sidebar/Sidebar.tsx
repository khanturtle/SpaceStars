'use client'

import Link from 'next/link'
import { usePathname } from 'next/navigation'

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
]

export default function Sidebar() {
  const pathname = usePathname()

  return (
    <section className={`${styles.sidebar} `}>
      <ul>
        {SIDE_LINK.map((item) => (
          <li
            key={item.index}
            className={
              pathname === item.href
                ? 'text-[color:var(--Main-Color-1,#6a64e9)] font-semibold'
                : 'text-[color:var(--Text-50, #9c9c9c)] font-normal'
            }
          >
            <div className={styles.i}>아</div>
            <Link href={item.href}>{item.title}</Link>
          </li>
        ))}
      </ul>
    </section>
  )
}
