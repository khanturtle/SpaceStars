import { create } from 'zustand'

import { GameType } from '@/apis/game'

interface GameState {
  selectedGames: GameType[]
  selectedGameIds: number[]
  selectedGamesCount: number
  addGame: (game: GameType) => void
  removeGame: (game: GameType) => void
  resetGames: () => void
}

const initialState = {
  selectedGames: [],
  selectedGameIds: [],
  selectedGamesCount: 0,
}

export const useGameStore = create<GameState>((set) => ({
  selectedGames: [],
  selectedGameIds: [],
  selectedGamesCount: 0,
  addGame: (game) =>
    set((state) => {
      if (state.selectedGamesCount < 3) {
        return {
          selectedGames: [...state.selectedGames, game],
          selectedGameIds: [...state.selectedGameIds, game.gameId],
          selectedGamesCount: state.selectedGamesCount + 1,
        }
      }
      return state
    }),
  removeGame: (game) =>
    set((state) => {
      return {
        selectedGames: state.selectedGames.filter(
          (g) => g.gameId !== game.gameId,
        ),
        selectedGameIds: state.selectedGameIds.filter(
          (id) => id !== game.gameId,
        ),
        selectedGamesCount: state.selectedGamesCount - 1,
      }
    }),
  resetGames: () => set(initialState),
}))
