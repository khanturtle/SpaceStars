'use client'

import { useRouter } from 'next/navigation'

import { type ElementRef, useEffect, useRef } from 'react'

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
    <div className="modal-backdrop">
      <dialog ref={dialogRef} className="modal" onClose={onDismiss}>
        {children}
        <button
          type="button"
          label="닫기"
          onClick={onDismiss}
          className="close-button"
        />
      </dialog>
    </div>
  )
}

export default Modal
