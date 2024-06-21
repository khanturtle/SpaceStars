'use client'

import { useRouter } from 'next/navigation'

import { GameListButton } from '@packages/ui'

import { GameType } from '@/apis/getGame'
import useDrag from '@/hooks/useDrag'

import styles from './teamList.module.css'

const GameItem = ({
  game,
  currentGame,
  onClick,
}: {
  game: GameType
  currentGame: string
  onClick: (game: GameType) => void
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
  games: GameType[]
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

  const UpdateGame = (item: GameType) => {
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
