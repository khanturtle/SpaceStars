'use client'

import { useRouter } from 'next/navigation'

import { CheckIcon, GameListButton } from '@packages/ui'

import styles from './teamList.module.css'

import { GameType } from '@/apis/game'
import { gamesImageUrl } from './state'

const GameItem = ({game, currentGame, onClick}: {
  game: GameType
  currentGame: string
  onClick: (gameId: string) => void
}) => {
  const gameImageUrl = gamesImageUrl[game.gameId]

  const item = {
    gameImage: gameImageUrl.gameImage,
    gameLogo: gameImageUrl.gameLogoImage,
    gameName: game.gameName,
  }
    
  return (
      <GameListButton
      // FIXME: game 데이터 수정
      item={item}
      onClick={() => onClick}
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

  const UpdateGame = (item: GameType) => {
    const currentParams = new URLSearchParams(searchParams)
    currentParams.set('game', item.gameName)
    router.push(`?${currentParams.toString()}`)
  }

  return (
    // TODO: 터치 스크롤 구현
    <div className={`${styles.gameList}`}>
      {games &&
        games.map((game) => (
          <GameItem key={game.index} game={game} currentGame={searchParams.game} onClick={()  => UpdateGame(game)} />
        ))}
    </div>
  )
}
