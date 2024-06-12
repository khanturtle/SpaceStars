import Link from 'next/link'

import styles from './sign.module.css'

interface DescriptionBoxProps {
  text: string
  textColor?: string
  link?: string
  linkName?: string
}

export default function DescriptionBox({
  text,
  textColor = 'color:var(--body-text-color,#333)',
  link = '',
  linkName = '',
}: DescriptionBoxProps) {
  return (
    <div className={styles['sign-up']}>
      <p className={`text-[${textColor}]`}>
        {text}
        {link && <Link href={link}>{linkName}</Link>}
      </p>
    </div>
  )
}
