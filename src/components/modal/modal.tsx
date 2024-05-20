'use client'

import { useRouter } from 'next/navigation'

import { type ElementRef, useEffect, useRef } from 'react'

import styles from './modal.module.css'

function Modal({ children }: { children: React.ReactNode }) {
  const router = useRouter()
  const dialogRef = useRef<ElementRef<'dialog'>>(null)

  useEffect(() => {
    if (!dialogRef.current?.open) {
      dialogRef.current?.showModal()
    }
  }, [])

  function onDismiss() {
    router.back()
  }

  return (
    <div className="absolute bg-[rgba(0,0,0,0.7)] flex justify-center items-center z-[1000] inset-0">
      <dialog
        ref={dialogRef}
        className={`w-[90%] h-[90%] sm:w-[80%] sm:h-[80%] 
                  md:w-[60%] md:h-[60%] lg:w-[50%] lg:h-[50%]
                  flex flex-col items-center justify-evenly
                  fixed inset-0 
                  bg-[url('/images/BG.svg')] bg-cover bg-center bg-no-repeat 
                  overflow-hidden
                  rounded-[10px]
                  `}
        onClose={onDismiss}
      >
        <div className="flex flex-col items-center justify-evenly">
          {children}
        </div>

        <button
          type="button"
          aria-label="닫기"
          onClick={onDismiss}
          className={styles['close-button']}
        />
      </dialog>
    </div>
  )
}

export default Modal
