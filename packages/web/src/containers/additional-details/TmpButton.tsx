'use client'

import { ModalContext } from '@/components/providers/modal-provider'
import { useContext } from 'react'

export default function TmpButton() {
  const { openModal, isModalOpen } = useContext(ModalContext)

  const handleClick = () => {
    openModal(<div className="px-[50px] py-[50px]">모달짠</div>)
    console.log(isModalOpen)
  }

  return (
    <>
      <button type="button" onClick={handleClick}>
        모달얍얍
      </button>
    </>
  )
}
