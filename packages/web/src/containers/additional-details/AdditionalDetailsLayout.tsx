'use client'

import { ModalContext } from '@/components/providers/modal-provider'
import { useContext } from 'react'
import AdditionalDetailsContainer from './AdditionalDetailsContainer'

// TODO: 다음 버튼 유효성 검증 및 disabled
export default function AdditionalDetailsLayout({
  className,
  step,
}: {
  className?: string
  step: string
}) {
  return (
    <div className="relative h-full px-[204px] pt-[90px] pb-[60px] flex flex-col items-center">
      <AdditionalDetailsContainer step={step} />
    </div>
  )
}

export const DevModalOpenBtn = () => {
  const { openModal } = useContext(ModalContext)

  return (
    <button
      type="button"
      onClick={() => openModal(<AdditionalDetailsLayout step="1" />)}
    >
      추가정보
    </button>
  )
}
