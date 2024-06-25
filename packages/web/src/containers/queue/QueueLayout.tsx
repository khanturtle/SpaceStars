'use client'

import styles from './queue.module.css'

import QueueButton from './QueueButton'
import QueueCardWrapper from './QueueCardWrapper'
import QueueDescription from './QueueDescription'

// TODO: 여기서 SSE, 큐 연결
export default function QueueLayout() {
  const isMatching = true
  return (
    <div className={styles.layout}>
      <QueueCardWrapper>
        <QueueCardWrapper.MatchingCard />
        <QueueCardWrapper.MatchingCard />
        <QueueCardWrapper.Loader isMatching={isMatching} />
      </QueueCardWrapper>

      <QueueButton>
        {isMatching && <QueueButton.MatchingButton />}
      </QueueButton>

      <QueueDescription isMatching={isMatching} />
    </div>
  )
}
