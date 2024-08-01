'use client'

import { useSession } from 'next-auth/react'

import { useContext, useEffect, useState } from 'react'

import { ArrowIcon } from '@packages/ui'

import { getMainGame } from '@/apis/getProfile'
import FormLayout from '@/components/form/formLayout'
import { ModalContext } from '@/components/providers/modal-provider'
import {
  useGameStore,
  useSelectedOption,
  useOptionStore,
} from '@/store/gameStore'

import AdditionalGames from './AdditionalGames'
import AdditionalMBTI from './AdditionalMBTI'
import AdditionalOptions from './AdditionalOptions'
import { updateProfile } from './action'

import styles from './additional.module.css'
import Toast from '@/components/Toast/toast'

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

export const AdditionalDetailsLayout = ({ token }: { token: string }) => {
  const { closeModal } = useContext(ModalContext)

  const [toastMessage, setToastMessage] = useState('')

  const [step, setStep] = useState<number>(1)
  const [mbtiId, setMbtiId] = useState<number>()

  const { selectedGameIds, resetGames } = useGameStore()
  const { selectedGameWithOption, resetOptions } = useSelectedOption()
  const { optionCount, resetOptionCount } = useOptionStore()

  const showToast = (text: string) => {
    setToastMessage(text)

    setTimeout(() => {
      setToastMessage('')
    }, 1500)
  }

  const handleNextStep = () => {
    if (step === 1 && selectedGameIds.length === 0) {
      showToast('게임을 선택하세요.')
    } else if (step === 2) {
      if (selectedGameWithOption === null) {
        showToast('옵션을 선택하세요.')
      } else {
        const objectKeys = Object.keys(selectedGameWithOption)
        if (objectKeys.length - 2 < optionCount) {
          showToast('옵션을 모두 선택하세요.')
        } else {
          setStep(step + 1)
        }
      }
    } else if (step + 1 <= 3) {
      setStep(step + 1)
    }
  }

  const handlePrevStep = () => {
    if (step === 2) {
      resetGames()
      resetOptions()
      resetOptionCount()
    } else if (step === 3) {
      resetOptions()
      resetOptionCount()
    }
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
      <>
        {toastMessage && (
          <Toast
            message={toastMessage}
            type="error"
            duration={1400}
            position="top"
            offsetY={100}
          />
        )}

        <FormLayout>
          <FormLayout.Legend
            title={'대표 게임을 선택해주세요'}
            description={`게임 1개를 선택해주세요`}
          />

          <AdditionalGames />

          <FormLayout.NextButton onClick={handleNextStep} />
          {/* <PassButton /> */}
        </FormLayout>
      </>
    )

  /** step 2: 대표 게임 선택 */
  if (step === 2)
    return (
      <>
        {toastMessage && (
          <Toast
            message={toastMessage}
            type="error"
            duration={1400}
            position="top"
            offsetY={100}
          />
        )}

        <FormLayout>
          <FormLayout.Legend
            title="대표 게임을 선택해주세요"
            description={`게임 옵션을 선택해주세요.`}
          />

          <AdditionalOptions />

          <FormLayout.PrevNextButton
            onPrevClick={handlePrevStep}
            onNextClick={handleNextStep}
          />
          {/* <PassButton /> */}
        </FormLayout>
      </>
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
        {/* <PassButton /> */}
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
      // 대표 게임 여부 확인
      if (data && !data?.result.main) {
        // false인 경우, 모달 열기
        openModal(
          <div
            className={`relative h-full flex flex-col items-center ${styles.container}`}
          >
            <AdditionalDetailsLayout token={token} />
          </div>,
          { isClose: false },
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
