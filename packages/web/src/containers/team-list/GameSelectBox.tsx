'use client'

import { useRouter } from 'next/navigation'

import { GameListButton } from '@packages/ui'

import useDrag from '@/hooks/useDrag'

import styles from './teamList.module.css'
import { GameTypes } from '@/types/type'

const GameItem = ({
  game,
  currentGame,
  onClick,
}: {
  game: GameTypes
  currentGame: string
  onClick: (game: GameTypes) => void
}) => {
  const item = {
    gameImage: game.gameImage,
    gameLogo: game.gameLogoImage,
    gameName: game.gameName,
  }

  return (
    <GameListButton
      className="min-w-[164px]"
      item={item}
      onClick={() => onClick(game)}
      isClicked={currentGame === game.gameName}
    />
  )
}

export default function GameSelectBox({
  games,
  searchParams,
}: {
  games: GameTypes[]
  searchParams: { [key: string]: string }
}) {
  const router = useRouter()
  const {
    isDragging,
    handleMouseDown,
    handleMouseUp,
    handleMouseMove,
    divRef,
  } = useDrag()

  const UpdateGame = (item: GameTypes) => {
    if (!isDragging) {
      return
    }
    const currentParams = new URLSearchParams(searchParams)
    currentParams.set('game', item.gameName)
    router.push(`?${currentParams.toString()}`)
  }

  return (
    <div
      className={`${styles.gameList}`}
      ref={divRef}
      onMouseDown={handleMouseDown}
      onMouseMove={handleMouseMove}
      onMouseUp={handleMouseUp}
    >
      {games &&
        games.map((game) => (
          <GameItem
            key={game.index}
            game={game}
            currentGame={searchParams.game}
            onClick={() => UpdateGame(game)}
          />
        ))}
    </div>
  )
}
