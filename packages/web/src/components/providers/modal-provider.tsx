'use client'

import { createContext, ReactNode, useState } from 'react'
import ModalClient from '../modal/modalClient'

interface ModalContextValue {
  isModalOpen: boolean
  modalContent: ReactNode
  openModal: (content: ReactNode) => void
  closeModal: () => void
}

export const ModalContext = createContext<ModalContextValue>({
  isModalOpen: false,
  modalContent: null,
  openModal: () => {},
  closeModal: () => {},
})

export const ModalProvider = ({ children }: { children: React.ReactNode }) => {
  const [isModalOpen, setIsModalOpen] = useState(false)
  const [modalContent, setModalContent] = useState<ReactNode | null>(null)

  const openModal = (content: ReactNode) => {
    setModalContent(content)
    setIsModalOpen(true)
  }

  const closeModal = () => {
    setIsModalOpen(false)
    setModalContent(null)
  }

  return (
    <ModalContext.Provider
      value={{ isModalOpen, modalContent, openModal, closeModal }}
    >
      {children}
      {isModalOpen && <ModalClient>{modalContent}</ModalClient>}
    </ModalContext.Provider>
  )
}
