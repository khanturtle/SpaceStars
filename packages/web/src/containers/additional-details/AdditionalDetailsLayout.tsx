'use client'

import { useContext, useState } from 'react'

import { ArrowIcon } from '@packages/ui'

import FormLayout from '@/components/form/formLayout'
import { ModalContext } from '@/components/providers/modal-provider'
import { useGameStore } from '@/store/gameStore'

import AdditionalGames from './AdditionalGames'
import AdditionalMBTI from './AdditionalMBTI'
import AdditionalOptions from './AdditionalOptions'

import styles from './additional.module.css'

/** 건너뛰기 버튼 */
const PassButton = () => {
  const { resetGames } = useGameStore()
  const { closeModal } = useContext(ModalContext)

  const handleClick = () => {
    resetGames()
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
export const AdditionalDetailsLayout = () => {
  const [step, setStep] = useState<number>(1)
  const { selectedGameIds } = useGameStore()

  const handleNextStep = () => {
    if (step + 1 <= 3) {
      setStep(step + 1)
    } else if (step + 1 === 4) {
      console.log('좋아하는 게임:', selectedGameIds)
      // console.log('mbti:', mbti)

      console.log('저장하고 닫기')
    }
  }

  const handlePrevStep = () => {
    if (step - 1 > 0) {
      setStep(step - 1)
    }
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
      <FormLayout>
        <FormLayout.Legend title="MBTI를 선택해주세요" />

        {/* <AdditionalMBTI mbti={mbti} onChange={(value: string) => setMbti(value)} /> */}

        <FormLayout.PrevNextButton
          onPrevClick={handlePrevStep}
          onNextClick={handleNextStep}
        />
        <PassButton />
      </FormLayout>
    )
  return null
}

export const DevModalOpenBtn = () => {
  const { openModal } = useContext(ModalContext)

  return (
    <button
      type="button"
      onClick={() =>
        openModal(
          <div
            className={`relative h-full flex flex-col items-center ${styles.container}`}
          >
            <AdditionalDetailsLayout />
          </div>,
        )
      }
    >
      추가정보
    </button>
  )
}
