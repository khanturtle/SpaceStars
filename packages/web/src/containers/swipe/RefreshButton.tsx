'use client'

import { useRouter } from 'next/navigation'
import styles from './swipe.module.css'

export default function RefreshButton({ nextPage }: { nextPage: number }) {
  const router = useRouter()

  // FIXME: Max 수정 전
  const handleRefresh = () => {
    if (nextPage === 5) {
      router.replace(`/dashboard/swipe?page=0`)
    } else {
      router.replace(`/dashboard/swipe?page=${nextPage}`)
    }
  }

  return (
    <div className={`${styles['refresh-button']}`}>
      <button type="button" aria-label="swipe-refresh" onClick={handleRefresh}>
        <p>새로고침</p>
      </button>
    </div>
  )
}
