'use client'

import { RotateCcw } from 'lucide-react'

import styles from './queue.module.css'

// TODO: 아이콘 받아오기
const RefreshButton = () => {
  return (
    <button type="button" className={styles.refresh}>
      <RotateCcw stroke="#fff" />
    </button>
  )
}

const MatchingButton = () => {
  return (
    <button type="button" className={styles.matchingBtn}>
      <RotateCcw stroke="#fff" />
    </button>
  )
}

export default function QueueButton({
  children,
}: {
  children?: React.ReactNode
}) {
  return (
    <div className={styles.buttons}>
      <RefreshButton />
      {children}
    </div>
  )
}

QueueButton.RefreshButton = RefreshButton
QueueButton.MatchingButton = MatchingButton
