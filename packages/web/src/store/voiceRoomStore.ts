import { create } from 'zustand'

interface VoiceState {
  isVoice: boolean
  setIsVoice: () => void
}

export const useVoiceStore = create<VoiceState>((set, get) => ({
  isVoice: false,
  setIsVoice: () => set((state) => ({ isVoice: !state.isVoice })),
}))
