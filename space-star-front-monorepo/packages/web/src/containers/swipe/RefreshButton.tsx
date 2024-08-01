import Link from 'next/link'

import styles from './swipe.module.css'

export default function RefreshButton({ nextPage }: { nextPage: number }) {
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
