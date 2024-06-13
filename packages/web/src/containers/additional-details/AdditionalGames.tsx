'use client'

import { useEffect, useState } from 'react'

import { GameType, getGames } from '@/apis/game'
import { useGameStore } from '@/store/gameStore'
import { GameButton } from '@packages/ui'

function GameSelectButton({ game }: { game: GameType }) {
  const { selectedGameIds, selectedGameIdsCount, addGame, removeGame } =
    useGameStore()

  const handleClick = () => {
    if (selectedGameIds.includes(game.gameId)) {
      removeGame(game.gameId)
    } else {
      if (selectedGameIdsCount < 3) {
        addGame(game.gameId)
      } else {
        // FIXME: alert 수정
        alert('더 이상 선택할 수 없어요')
      }
    }
  }

  return (
    <GameButton
      item={{
        gameImage: game.gameLogoImage,
        gameName: game.gameNameKor,
      }}
      isClicked={selectedGameIds.includes(game.gameId)}
      onClick={handleClick}
    />
  )
}

export default function AdditionalGames() {
  const [games, setGames] = useState<GameType[]>([])

  useEffect(() => {
    const getGame = async () => {
      const res = await getGames()
      console.log(res)

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
