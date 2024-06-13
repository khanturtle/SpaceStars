'use client'

import { ModalContext } from '@/components/providers/modal-provider'
import { useContext, useState } from 'react'
import styles from './additional.module.css'
import FormLayout from '@/components/form/formLayout'
import AdditionalGames from './AdditionalGames'
import { useRouter } from 'next/navigation'
import { ItemType } from './state'
import { useGameStore } from '@/store/gameStore'

// TODO: 다음 버튼 유효성 검증 및 disabled
export const AdditionalDetailsLayout = () => {
  const [step, setStep] = useState<number>(1)

  const router = useRouter()

  const handleNextStep = () => {
    const nextStep: number = step + 1

    if (nextStep <= 3) {
      // setStep(step + 1)
    } else if (nextStep === 4) {
      // console.log('likeGames', selectedGames)
      // console.log('mbti:', mbti)
      console.log('저장하고 닫기')
    }
  }
  const handlePrevStep = () => {
    const prevStep = step - 1
    if (prevStep > 0) {
      router.replace(`/additional-details?step=${prevStep}`)
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
        <FormLayout.PassButton />
      </FormLayout>
    )

  return null
  // <AdditionalDetailsContainer step="1" />
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
