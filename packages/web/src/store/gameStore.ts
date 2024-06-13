import { create } from 'zustand'

interface GameState {
  selectedGameIds: number[]
  selectedGameIdsCount: number
  addGame: (gameId: number) => void
  removeGame: (gameId: number) => void
}

export const useGameStore = create<GameState>((set, get) => ({
  selectedGameIds: [],
  selectedGameIdsCount: 0,
  addGame: (gameId) =>
    set((state) => {
      if (state.selectedGameIdsCount < 3) {
        return {
          selectedGameIds: [...state.selectedGameIds, gameId],
          selectedGameIdsCount: state.selectedGameIdsCount + 1,
        }
      }
      return state
    }),
  removeGame: (gameId) =>
    set((state) => {
      return {
        selectedGameIds: state.selectedGameIds.filter((g) => g !== gameId),
        selectedGameIdsCount: state.selectedGameIdsCount - 1,
      }
    }),
}))
