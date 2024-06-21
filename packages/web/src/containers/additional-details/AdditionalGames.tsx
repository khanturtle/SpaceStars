'use client'

import { useEffect, useState } from 'react'

import { GameButton } from '@packages/ui'

import { getGames } from '@/apis/getGame'
import { useGameStore } from '@/store/gameStore'
import { GameTypes } from '@/types/type'

function GameSelectButton({ game }: { game: GameTypes }) {
  const { selectedGames, selectedGamesCount, addGame, removeGame } =
    useGameStore()

  // FIXME: 다음 단계에서 이전으로 돌아가면, 선택은 해제되어 있고, 값은 들어있음
  // 불변성? 때문인지 확인 필요
  const handleClick = () => {
    if (selectedGames.includes(game)) {
      removeGame(game)
    } else {
      if (selectedGamesCount < 3) {
        addGame(game)
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
      isClicked={selectedGames.includes(game)}
      onClick={handleClick}
    />
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
