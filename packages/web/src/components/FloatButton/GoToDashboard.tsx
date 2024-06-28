import { Session } from 'next-auth'
import Link from 'next/link'

import styles from './floatbutton.module.css'

export default function GoToDashboard({
  session,
}: {
  session: Session | null
}) {
  const href = session ? '/dashboard' : '/sign-up'

  return (
    <Link href={href} className={styles['animated-button']}>
      <svg
        viewBox="0 0 24 24"
        className={styles['arr-2']}
        xmlns="http://www.w3.org/2000/svg"
      >
        <path d="M16.1716 10.9999L10.8076 5.63589L12.2218 4.22168L20 11.9999L12.2218 19.778L10.8076 18.3638L16.1716 12.9999H4V10.9999H16.1716Z"></path>
      </svg>
      <span className={styles['text']}>Get Started</span>
      <span className={styles['circle']}></span>
      <svg
        viewBox="0 0 24 24"
        className={styles['arr-1']}
        xmlns="http://www.w3.org/2000/svg"
      >
        <path d="M16.1716 10.9999L10.8076 5.63589L12.2218 4.22168L20 11.9999L12.2218 19.778L10.8076 18.3638L16.1716 12.9999H4V10.9999H16.1716Z"></path>
      </svg>
    </Link>
  )
}
