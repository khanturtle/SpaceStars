import Link from 'next/link'

import styles from './swipe.module.css'

export default function RefreshButton({ nextPage }: { nextPage: number }) {
  // TODO: 위치 Nav 위로 수정 필요
  return (
    <div className={`${styles['refresh-button']}`}>
      <Link
        href={`/dashboard/swipe?page=${nextPage}`}
        passHref
        replace
        className="inline-block"
      >
        <p>새로고침</p>
      </Link>
    </div>
  )
}
