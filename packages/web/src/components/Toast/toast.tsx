'use client'

import { useState, useEffect } from 'react'

import styles from './toast.module.css'

interface ToastProps {
  message: string
  type?: 'info' | 'warning' | 'error'
  duration?: number
  position?: 'top' | 'bottom'
  offsetY?: number
}

const Toast = ({
  message,
  type = 'info',
  duration = 3000,
  position = 'bottom',
  offsetY = 20,
}: ToastProps) => {
  const [isVisible, setIsVisible] = useState(false)

  useEffect(() => {
    setIsVisible(true)
    const timer = setTimeout(() => {
      setIsVisible(false)
    }, duration)

    return () => clearTimeout(timer)
  }, [message, type, duration])

  return (
    <div
      className={`${styles['toast-container']} ${styles[type]} ${isVisible ? styles.visible : ''}`}
      style={{ [position === 'top' ? 'top' : 'bottom']: `${offsetY}px` }}
    >
      <div className={styles['toast-message']}>{message}</div>
    </div>
  )
}

export default Toast
