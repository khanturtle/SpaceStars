'use client'

import { useRouter } from 'next/navigation'
import styles from './swipe.module.css'

export default function RefreshButton({ nextPage }: { nextPage: number }) {
  const router = useRouter()

  const handleRefresh = () => {
    router.replace(`/dashboard/swipe?page=${nextPage}`)
  }

  return (
    <div className={`${styles['refresh-button']}`}>
      <button type="button" aria-label="swipe-refresh" onClick={handleRefresh}>
        <p>새로고침</p>
      </button>
    </div>
  )
}
