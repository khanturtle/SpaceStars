'use client'

import { useRouter } from 'next/navigation'
import styles from './swipe.module.css'

export default function RefreshButton({ nextPage }: { nextPage: number }) {
  const router = useRouter()

  const handleRefresh = () => {
    router.replace(`/dashboard/swipe?page=${nextPage}`)
  }

  // TODO: 위치 Nav 위로 수정 필요
  return (
    <div className={`${styles['refresh-button']}`}>
      <button type="button" aria-label="swipe-refresh" onClick={handleRefresh}>
        <p>새로고침</p>
      </button>
    </div>
  )
}
