'use client'

import { useState, createContext, useContext, ReactNode } from 'react'

// Toast Context
interface Toast {
  id: string
  message: string
  type: 'success' | 'error' | 'info'
}

type ToastContextType = {
  toasts: Toast[]
  addToast: (toast: Toast) => void
  removeToast: (id: string) => void
}

const ToastContext = createContext<ToastContextType | null>(null)

// Toast Provider
export const ToastProvider = ({ children }: { children: ReactNode }) => {
  const [toasts, setToasts] = useState<Toast[]>([])

  // Add a new toast
  const addToast = (toast: Toast) => {
    const newToast = { ...toast, id: Date.now().toString() }
    setToasts((prevToasts) => [...prevToasts, newToast])

    // Remove the toast after 1 second
    const timer = setTimeout(() => {
      removeToast(newToast.id)
    }, 1000)

    return () => clearTimeout(timer)
  }

  // Remove a toast
  const removeToast = (id: string) => {
    setToasts((prevToasts) => prevToasts.filter((t) => t.id !== id))
  }

  return (
    <ToastContext.Provider value={{ toasts, addToast, removeToast }}>
      {children}
    </ToastContext.Provider>
  )
}

export const useToast = () => {
  const context = useContext(ToastContext)
  if (!context) {
    throw new Error('useToast must be used within a ToastProvider')
  }
  return context
}
