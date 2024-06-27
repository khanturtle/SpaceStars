'use client'

import { PlayIcon, RefreshIcon } from '@packages/ui'

import styles from './queue.module.css'

/** 큐 취소 */
const RefreshButton = ({ onClick }: { onClick: () => void }) => {
  return (
    <button type="button" className={styles.refresh} onClick={onClick}>
      <RefreshIcon />
    </button>
  )
}

/** 큐 수락 */
const MatchingButton = ({ onClick }: { onClick: () => void }) => {
  return (
    <button type="button" className={styles.matchingBtn} onClick={onClick}>
      <PlayIcon />
    </button>
  )
}

export default function QueueButton({
  children,
}: {
  children?: React.ReactNode
}) {
  return <div className={styles.buttons}>{children}</div>
}

QueueButton.RefreshButton = RefreshButton
QueueButton.MatchingButton = MatchingButton
