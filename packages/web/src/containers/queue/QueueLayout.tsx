'use client'

import { useRouter } from 'next/navigation'
import { useSession } from 'next-auth/react'

import { useContext, useEffect, useReducer, useState } from 'react'

import { Button } from '@packages/ui'

import { enteredQueue, leftQueue } from '@/apis/actionQueue'
import { getMbtiById } from '@/apis/getMbti'

import { ModalContext } from '@/components/providers/modal-provider'
import FormLayout from '@/components/form/formLayout'
import { useToast } from '@/components/Toast/toast-provider'

import { useSSEMatchingConnection } from '@/hooks/useSSEMatchingConnection'
import { getQueueDataByUuid } from '@/lib/getQueueDataByClient'

import QueueButton from './QueueButton'
import QueueCardWrapper from './QueueCardWrapper'
import QueueDescription from './QueueDescription'

import styles from './queue.module.css'

const QuitModal = () => {
  const { closeModal } = useContext(ModalContext)

  const router = useRouter()

  /** 큐를 퇴장하고 페이지 이동 */
  const handleClick = async () => {
    // 큐 퇴장
    await leftQueue()
    // 모달 닫기
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

export default function QueueLayout({
  myData,
  mbtiName,
  connectedGame,
  uuid,
}: {
  myData: any
  mbtiName: string
  connectedGame: string
  uuid: string
}) {
  // SSE 연결
  const { eventSource, isConnected, showErrorModal, setShowErrorModal } =
    useSSEMatchingConnection(uuid, connectedGame)

  // 매칭 완료면 true, 매칭 중이면 false
  const [isMatching, setIsMatching] = useReducer((state) => !state, false)

  const { openModal } = useContext(ModalContext)
  const { showToast } = useToast()
  const { data: session } = useSession()

  const [matchUuid, setMatchUuid] = useState('lmn456')
  const [matchData, setMatchData] = useState<any>()
  const [matchMbti, setMatchMbti] = useState<string>('')

  const router = useRouter()

  /** 큐를 취소하고 이전 페이지로 */
  const handleRestart = () => {
    openModal(<QuitModal />, { isClose: false })
  }

  // 오류 모달 표시
  useEffect(() => {
    if (showErrorModal) {
      showToast({
        message: '서버 에러가 발생했습니다. 잠시후 다시 시도해주세요.',
        type: 'error',
        position: 'bottom',
      })
      leftQueue()
      router.replace('/dashboard/queue')
    }
  }, [showErrorModal])

  useEffect(() => {
    const actionQueue = async () => {
      const res = await enteredQueue(connectedGame)
      if (!res) {
        showToast({
          message: '서버 에러가 발생했습니다. 잠시후 다시 시도해주세요.',
          type: 'error',
          position: 'bottom',
        })
        router.replace('/dashboard/queue')
      }
    }
    actionQueue()
  }, [])

  useEffect(() => {
    if (eventSource) {
      const enteredGame = async () => {}
      enteredGame()

      return () => {
        eventSource.close()
      }
    }
    return undefined
  }, [eventSource])

  /** 5초 뒤 전환 */
  // useEffect(() => {
  //   const interval = setInterval(() => {
  //     setIsMatching()
  //   }, 5000)

  //   return () => clearInterval(interval)
  // }, [])

  /** 상대방 정보 */
  useEffect(() => {
    if (!session) return
    const fetchData = async () => {
      const token = session?.user?.data.accessToken

      const res = await getQueueDataByUuid(matchUuid, token)
      if (res) {
        setMatchData(res)
      }
      if (res.profileInfo?.mbtiId) {
        const mbti = await getMbtiById(res.profileInfo.mbtiId)
        setMatchMbti(mbti?.mbtiName ?? '')
      }
    }

    fetchData()
  }, [isMatching])

  return (
    <div className={styles.layout}>
      <QueueCardWrapper>
        {/* 내카드 */}
        <QueueCardWrapper.FrontCard data={myData} mbtiName={mbtiName} />

        {/* 상대방 카드 */}
        {isMatching ? (
          <QueueCardWrapper.FrontCard data={matchData} mbtiName={matchMbti} />
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
