'use client'

import { useState } from 'react'

import QueueButton from './QueueButton'
import QueueCardWrapper from './QueueCardWrapper'
import QueueDescription from './QueueDescription'

import styles from './queue.module.css'
import FrontCard from '@/components/Card/FrontCard'

// TODO: 여기서 SSE, 큐 연결
export default function QueueLayout({
  myData,
  mbtiName,
}: {
  myData: any
  mbtiName: string
}) {
  // 매칭 완료면 true, 매칭 중이면 false
  const [isMatching, setIsMatching] = useState(false)

  return (
    <div className={styles.layout}>
      <QueueCardWrapper>
        {/* 내카드 */}
        <QueueCardWrapper.FrontCard myData={myData} mbtiName={mbtiName} />

        {/* 상대방 카드 */}
        {isMatching ? (
          <QueueCardWrapper.MatchingCard />
        ) : (
          <QueueCardWrapper.MatchingCard />
        )}
        <QueueCardWrapper.Loader isMatching={isMatching} />
      </QueueCardWrapper>

      <QueueButton>{isMatching && <QueueButton.MatchingButton />}</QueueButton>

      <QueueDescription isMatching={isMatching} />
    </div>
  )
}
