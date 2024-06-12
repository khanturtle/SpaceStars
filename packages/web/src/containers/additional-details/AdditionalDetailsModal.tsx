'use client'

import { useContext } from 'react'

import { ModalContext } from '@/components/providers/modal-provider'

export default function AdditionalDetailsModal() {
  const { isModalOpen, closeModal } = useContext(ModalContext)

  return null
}
