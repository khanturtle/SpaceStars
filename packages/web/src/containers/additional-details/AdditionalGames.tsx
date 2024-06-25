'use client'

import { useEffect, useState } from 'react'

import { GameButton } from '@packages/ui'

import { getGames } from '@/apis/getGame'
import { useGameStore } from '@/store/gameStore'
import { GameTypes } from '@/types/type'
import Toast from '@/components/Toast/toast'

function GameSelectButton({ game }: { game: GameTypes }) {
  const { selectedGames, selectedGamesCount, addGame, removeGame } =
    useGameStore()
  const [toastMessage, setToastMessage] = useState('')

  const showToast = () => {
    setToastMessage('더 이상 선택할 수 없어요')

    setTimeout(() => {
      setToastMessage('')
    }, 1500)
  }

  const handleClick = () => {
    if (selectedGames.includes(game)) {
      removeGame(game)
    } else {
      if (selectedGamesCount < 3) {
        addGame(game)
      } else {
        showToast()
      }
    }
  }

  return (
    <>
      {toastMessage && (
        <Toast
          message={toastMessage}
          type="error"
          duration={1400}
          position="top"
          offsetY={100}
        />
      )}
      <GameButton
        item={{
          gameImage: game.gameLogoImage,
          gameName: game.gameNameKor,
        }}
        isClicked={selectedGames.includes(game)}
        onClick={handleClick}
      />
    </>
  )
}

export default function AdditionalGames() {
  const [games, setGames] = useState<GameTypes[]>([])

  useEffect(() => {
    const getGame = async () => {
      const res = await getGames()

      if (res) {
        setGames(res)
      }
    }

    getGame()
  }, [])

  return (
    <div className="flex flex-wrap justify-start flex-row gap-[17px_16px] mb-[47px]">
      {games &&
        games.map((game) => (
          <div
            key={game.index}
            className={`${game.index === games.length - 1 ? 'mr-auto' : ''}`}
          >
            <GameSelectButton game={game} />
          </div>
        ))}
    </div>
  )
}
