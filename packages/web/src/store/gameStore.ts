import { create } from 'zustand'

import { GameOptionDetailType, GameType } from '@/apis/getGame'

interface GameState {
  selectedGames: GameType[]
  selectedGameIds: number[]
  selectedGamesCount: number
  addGame: (game: GameType) => void
  removeGame: (game: GameType) => void
  resetGames: () => void
}

const initialGameState = {
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
  resetGames: () => set(initialGameState),
}))

/** 대표 게임 옵션 선택 */
export interface GameOptionType {
  gameId: number
  tierId?: number
  positionId?: number
  classId?: number
  serverId?: number
  gameNickname?: string
  main: boolean
}

interface SelectedOptionStore {
  selectedGameWithOption: GameOptionType | null
  setGameWithOption: (
    gameId: number,
    optionType: string,
    option: GameOptionDetailType,
  ) => void
  resetOptions: () => void
}

const initialOptionState = {
  selectedGameWithOption: null,
}

export const useSelectedOption = create<SelectedOptionStore>((set) => ({
  selectedGameWithOption: null,
  setGameWithOption: (gameId, optionType, option) =>
    set((state) => {
      let updatedGameOption: GameOptionType | null = {
        ...state.selectedGameWithOption,
        gameId: gameId,
        main: true,
      }

      if (updatedGameOption) {
        switch (optionType) {
          case 'isTier':
            updatedGameOption = { ...updatedGameOption, tierId: option.id }
            break
          case 'isPosition':
            updatedGameOption = {
              ...updatedGameOption,
              positionId: option.id,
            }
            break
          case 'isClass':
            updatedGameOption = {
              ...updatedGameOption,
              classId: option.id,
            }
            break
          case 'isServer':
            updatedGameOption = {
              ...updatedGameOption,
              serverId: option.id,
            }
            break
          default:
            break
        }
      }

      return {
        selectedGameWithOption: updatedGameOption,
      }
    }),
  resetOptions: () => set(initialOptionState),
}))
