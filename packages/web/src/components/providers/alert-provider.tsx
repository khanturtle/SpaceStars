"use client"

import {
  Alert,
  AlertTitle,
  AlertDescription,
  alertVariants,
} from '@/components/ui/alert'
import { VariantProps } from 'class-variance-authority'
import { createContext, useState, ReactNode } from 'react'

interface AlertContextType {
  show: (props: AlertProps) => void
}

export const AlertContext = createContext<AlertContextType | null>(null)

interface AlertProps extends VariantProps<typeof alertVariants> {
  title?: string
  description?: string
}

export const AlertProvider = ({ children }: { children: ReactNode }) => {
  const [alertProps, setAlertProps] = useState<AlertProps | null>(null)

  const show = (props: AlertProps) => {
    setAlertProps(props)
  }

  return (
    <AlertContext.Provider value={{ show }}>
      {alertProps && (
        <Alert variant={alertProps.variant}>
          <AlertTitle>{alertProps.title}</AlertTitle>
          <AlertDescription>{alertProps.description}</AlertDescription>
        </Alert>
      )}
      {children}
    </AlertContext.Provider>
  )
}
