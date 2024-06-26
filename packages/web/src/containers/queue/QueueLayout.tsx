'use client'

import { useRouter } from 'next/navigation'

import { useContext, useEffect, useReducer } from 'react'

import { EventSourcePolyfill } from 'event-source-polyfill'

import { Button } from '@packages/ui'

import { ModalContext } from '@/components/providers/modal-provider'
import FormLayout from '@/components/form/formLayout'

import QueueButton from './QueueButton'
import QueueCardWrapper from './QueueCardWrapper'
import QueueDescription from './QueueDescription'

import styles from './queue.module.css'
import { useSSEMatchingConnection } from '@/hooks/useSSEMatchingConnection'
import { useToast } from '@/components/Toast/toast-provider'

const QuitModal = () => {
  const { closeModal } = useContext(ModalContext)

  const router = useRouter()

  /** 큐를 퇴장하고 페이지 이동 */
  const handleClick = () => {
    // 큐 퇴장

    closeModal()
    router.replace('/dashboard/queue')
  }

  return (
    <div className="relative flex flex-col items-center h-full">
      <FormLayout
        className="relative h-full px-[204px] pt-[90px] pb-[85px]
                  flex flex-col items-center"
      >
        <FormLayout.Legend
          title="Quit Matching"
          description={`큐를 취소하고 이전 페이지로 이동합니다.`}
        />
        <Button label="확인" onClick={handleClick} />
      </FormLayout>
    </div>
  )
}

// TODO: 여기서 SSE, 큐 연결
export default function QueueLayout({
  myData,
  mbtiName,
  connectedGame,
  uuid,
  token,
}: {
  myData: any
  mbtiName: string
  connectedGame: string
  uuid: string
  token: string
}) {
  // 매칭 완료면 true, 매칭 중이면 false
  const [isMatching, setIsMatching] = useReducer((state) => !state, false)

  const { openModal } = useContext(ModalContext)

  /** 큐를 취소하고 이전 페이지로 */
  const handleRestart = () => {
    openModal(<QuitModal />, { isClose: false })
  }

  // const eventSource = new EventSource('/sse-endpoint', {
  //   method: 'POST',
  //   body: JSON.stringify({ gameName: connectedGame }),
  //   headers: {
  //     'Content-Type': 'application/json',
  //   },
  // })
  const { eventSource, isConnected, showErrorModal, setShowErrorModal } =
    useSSEMatchingConnection(uuid, token)
  const { showToast } = useToast()
  const router = useRouter()

  useEffect(() => {
    // 오류 모달 표시
    if (showErrorModal) {
      showToast({
        message: '서버 에러가 발생했습니다. 잠시후 다시 시도해주세요.',
        type: 'error',
        position: 'bottom',
      })
      router.replace('/dashboard/queue')
    }
  }, [showErrorModal])

  useEffect(() => {
    if (eventSource) {
      return () => {
        eventSource.close()
      }
    }
    return undefined
  }, [])

  return (
    <div className={styles.layout}>
      <QueueCardWrapper>
        {/* 내카드 */}
        <QueueCardWrapper.FrontCard data={myData} mbtiName={mbtiName} />

        {/* 상대방 카드 */}
        {isMatching ? (
          // TODO: 잡힌 상대방 정보
          <QueueCardWrapper.FrontCard data={null} mbtiName={''} />
        ) : (
          <QueueCardWrapper.MatchingCard />
        )}
        <QueueCardWrapper.Loader isMatching={isMatching} />
      </QueueCardWrapper>

      <QueueButton>
        <QueueButton.RefreshButton onClick={handleRestart} />
        {isMatching && <QueueButton.MatchingButton onClick={() => {}} />}
      </QueueButton>

      <QueueDescription isMatching={isMatching} />
    </div>
  )
}
