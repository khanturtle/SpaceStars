'use client'

import { useSession } from 'next-auth/react'

import { useContext, useEffect, useState } from 'react'

import { ArrowIcon } from '@packages/ui'

import { getMainGame } from '@/apis/getProfile'
import FormLayout from '@/components/form/formLayout'
import { ModalContext } from '@/components/providers/modal-provider'
import { useGameStore, useSelectedOption } from '@/store/gameStore'

import AdditionalGames from './AdditionalGames'
import AdditionalMBTI from './AdditionalMBTI'
import AdditionalOptions from './AdditionalOptions'
import { updateProfile } from './action'

import styles from './additional.module.css'

/** 건너뛰기 버튼 */
const PassButton = () => {
  const { resetGames } = useGameStore()
  const { resetOptions } = useSelectedOption()
  const { closeModal } = useContext(ModalContext)

  const handleClick = () => {
    resetGames()
    resetOptions()
    closeModal()
  }

  return (
    <div className="flex justify-center w-full mt-6">
      <button
        type="button"
        onClick={handleClick}
        className="inline-flex items-center justify-center gap-8"
      >
        <i>
          <ArrowIcon width="14" height="14" type="right" fill="white" />
        </i>
        <p className="text-white text-center text-base not-italic font-normal leading-[170%]">
          건너뛰기
        </p>
      </button>
    </div>
  )
}

// TODO: 다음 버튼 유효성 검증 및 disabled
export const AdditionalDetailsLayout = ({ token }: { token: string }) => {
  const [step, setStep] = useState<number>(1)
  const [mbtiId, setMbtiId] = useState<number>()
  const { closeModal } = useContext(ModalContext)

  const { selectedGameIds } = useGameStore()
  const { selectedGameWithOption } = useSelectedOption()

  const handleNextStep = () => {
    if (step + 1 <= 3) {
      setStep(step + 1)
    }
  }

  const handlePrevStep = () => {
    if (step - 1 > 0) {
      setStep(step - 1)
    }
  }

  /** 회원 정보 제출 */
  const handleSubmitDetails = () => {
    updateProfile(selectedGameIds, selectedGameWithOption!, mbtiId!, token)

    closeModal()
  }

  /** step 1: 게임 선택 */
  if (step === 1)
    return (
      <FormLayout>
        <FormLayout.Legend
          title={'좋아하는 게임을 선택해주세요'}
          description={`최대 3개까지 선택 가능합니다.`}
        />

        <AdditionalGames />

        <FormLayout.NextButton onClick={handleNextStep} />
        <PassButton />
      </FormLayout>
    )

  /** step 2: 대표 게임 선택 */
  if (step === 2)
    return (
      <FormLayout>
        <FormLayout.Legend
          title="대표 게임을 선택해주세요"
          description={`게임 1개를 골라 옵션을 선택해주세요.`}
        />

        <AdditionalOptions />

        <FormLayout.PrevNextButton
          onPrevClick={handlePrevStep}
          onNextClick={handleNextStep}
        />
        <PassButton />
      </FormLayout>
    )

  /** step 3: MBTI 선택 */
  if (step === 3)
    return (
      <FormLayout className="relative">
        <FormLayout.Legend title="MBTI를 선택해주세요" />

        <AdditionalMBTI mbtiId={mbtiId} setMbtiId={setMbtiId} />

        <FormLayout.PrevNextButton
          nextLabel="완료"
          onPrevClick={handlePrevStep}
          onNextClick={handleSubmitDetails}
        />
        <PassButton />
      </FormLayout>
    )
  return null
}

export const DevModalOpen = () => {
  const { data: session, status } = useSession()

  const { openModal } = useContext(ModalContext)

  useEffect(() => {
    const fetchData = async (token: string) => {
      const data = await getMainGame(token)

      if (!data?.result.main) {
        // false인 경우, 모달 열기
        openModal(
          <div
            className={`relative h-full flex flex-col items-center ${styles.container}`}
          >
            <AdditionalDetailsLayout token={token} />
          </div>,
        )
      }
    }

    if (status === 'authenticated') {
      const token = (session?.user?.data.accessToken as string) || ''

      fetchData(token)
    }
  }, [status])

  // 컴포넌트는 모달을 열고 종료
  return null
}
