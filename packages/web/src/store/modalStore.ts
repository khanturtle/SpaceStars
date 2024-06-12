import { create } from 'zustand'

interface ModalOpenType {
  isModalOpen: boolean
  openModal: () => void
  closeModal: () => void
}

export const useModalStore = create<ModalOpenType>((set) => ({
  isModalOpen: false,
  openModal: () => set({ isModalOpen: true }),
  closeModal: () => set({ isModalOpen: false }),
}))
