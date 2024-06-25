'use client'

import { createContext, ReactNode, useState } from 'react'

import ModalClient from '../modal/modalClient'

interface ModalContextValue {
  isModalOpen: boolean
  modalContent: ReactNode
  isClose: boolean
  openModal: (content: ReactNode, options?: { isClose?: boolean }) => void
  closeModal: () => void
}

export const ModalContext = createContext<ModalContextValue>({
  isModalOpen: false,
  modalContent: null,
  isClose: true,
  openModal: () => {},
  closeModal: () => {},
})

export const ModalProvider = ({
  children,
  isClose = true,
}: {
  children: React.ReactNode
  isClose?: boolean
}) => {
  const [isModalOpen, setIsModalOpen] = useState(false)
  const [modalContent, setModalContent] = useState<ReactNode | null>(null)
  const [isCloseModal, setIsCloseModal] = useState(isClose)

  const openModal = (content: ReactNode, options?: { isClose?: boolean }) => {
    setModalContent(content)
    setIsModalOpen(true)

    if (options?.isClose !== undefined) {
      setIsCloseModal(options.isClose)
    }
  }

  const closeModal = () => {
    setIsModalOpen(false)
    setModalContent(null)
  }

  return (
    <ModalContext.Provider
      value={{ isModalOpen, modalContent, openModal, closeModal, isClose }}
    >
      {children}
      {isModalOpen && (
        <ModalClient isClose={isCloseModal}>{modalContent}</ModalClient>
      )}
    </ModalContext.Provider>
  )
}
