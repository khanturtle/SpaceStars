'use client'

import { usePathname } from 'next/navigation'
import styles from './Sidebar.module.css'

export default function RightSidebar() {
  const pathName = usePathname()

  console.log(pathName)
  if (pathName === '/dashboard/chat') {
    return <div className={styles.rightSidebar}>채팅</div>
  }
  return <div className={styles.rightSidebar}>오른쪽 </div>
}
