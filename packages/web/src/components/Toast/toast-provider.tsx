'use client'

import { useState, createContext, useContext, useEffect } from 'react'
import styles from './toast.module.css'

interface ToastProps {
  message: string
  type?: 'info' | 'warning' | 'error'
  duration?: number
  position?: 'top' | 'bottom'
  offsetY?: number
}

interface ToastContextValue {
  showToast: (props: ToastProps) => void
}

const ToastContext = createContext<ToastContextValue>({
  showToast: () => {},
})

export const ToastProvider = ({ children }: { children: React.ReactNode }) => {
  const [toastProps, setToastProps] = useState<ToastProps | null>(null)

  useEffect(() => {
    let timer: ReturnType<typeof setTimeout> | null = null

    if (toastProps) {
      setIsVisible(true)
      timer = setTimeout(() => {
        setIsVisible(false)
      }, toastProps.duration || 3000)
    }

    return () => {
      if (timer) {
        clearTimeout(timer)
      }
    }
  }, [toastProps])

  const showToast = (props: ToastProps) => {
    setToastProps(props)
  }

  const [isVisible, setIsVisible] = useState(false)

  return (
    <ToastContext.Provider value={{ showToast }}>
      {toastProps && isVisible && (
        <div
          className={`${styles['toast-container']} ${styles[toastProps.type || 'info']}  ${isVisible ? styles.visible : ''}`}
          style={{
            [toastProps.position === 'top' ? 'top' : 'bottom']:
              `${toastProps.offsetY || 20}px`,
          }}
        >
          <div className={styles['toast-message']}>{toastProps.message}</div>
        </div>
      )}
      {children}
    </ToastContext.Provider>
  )
}

export const useToast = () => useContext(ToastContext)
