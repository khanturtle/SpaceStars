'use client'

import { useRouter } from 'next/navigation'

import { CloseIcon } from '@packages/ui'

import styles from './modal.module.css'

function FakeModal({
  className,
  children,
  onCloseRoute,
}: {
  className?: string
  children: React.ReactNode
  onCloseRoute?: string
}) {
  const router = useRouter()

  function onDismiss() {
    if (onCloseRoute) {
      router.replace(onCloseRoute)
    } else {
      router.back()
    }
  }

  return (
    <div className="absolute bg-[rgba(0,0,0,0.7)] flex justify-center items-center z-[1000] inset-0 h-full">
      <div
        className={`relative flex flex-col items-center justify-around
                  inset-0 bg-white overflow-hidden rounded-[10px]
                  ${className}`}
      >
        <div
          className={`w-full h-full 
                  bg-[url('/images/BG.svg')] bg-cover bg-center bg-no-repeat 
                  overflow-hidden rounded-[10px]`}
        >
          {children}

          <button
            type="button"
            aria-label="닫기"
            onClick={onDismiss}
            className={styles['close-button']}
          >
            <CloseIcon />
          </button>
        </div>
      </div>
    </div>
  )
}

export default FakeModal
