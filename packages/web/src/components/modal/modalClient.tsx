'use client'

import { type ElementRef, useEffect, useRef, useContext } from 'react'
import { createPortal } from 'react-dom'

import { CloseIcon } from '@packages/ui'

import { ModalContext } from '../providers/modal-provider'

import styles from './modal.module.css'

function ModalClient({
  className,
  children,
  isClose = true,
}: {
  className?: string
  isClose?: boolean
  children: React.ReactNode
}) {
  const dialogRef = useRef<ElementRef<'dialog'>>(null)
  const modalRoot = document.getElementById('modal-root') || document.body

  const { isModalOpen, closeModal } = useContext(ModalContext)

  useEffect(() => {
    if (isModalOpen && dialogRef.current?.open) {
      dialogRef.current?.showModal()
    } else if (!isModalOpen && dialogRef.current?.open) {
      dialogRef.current?.close()
    }
  }, [isModalOpen])

  function onDismiss() {
    closeModal()
  }

  return createPortal(
    <div className="absolute bg-[rgba(0,0,0,0.7)] flex justify-center items-center z-[9000] inset-0 h-full">
      <dialog
        ref={dialogRef}
        className={`flex flex-col items-center justify-around fixed inset-0 
                  bg-[url('/images/BG.svg')] bg-cover bg-center bg-no-repeat 
                  overflow-hidden rounded-[10px]
                  ${className}`}
        onClose={onDismiss}
      >
        {children}
        {isClose && (
          <button
            type="button"
            aria-label="닫기"
            onClick={onDismiss}
            className={styles['close-button']}
          >
            <CloseIcon />
          </button>
        )}
      </dialog>
    </div>,
    modalRoot,
  )
}

export default ModalClient
