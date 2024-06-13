'use client'

import { useRouter } from 'next/navigation'

import { GameListButton } from '@packages/ui'

import styles from './teamList.module.css'

import { GameType } from '@/apis/game'
import { gamesImageUrl } from '@/apis/state'

const GameItem = ({game, currentGame, onClick}: {
  game: GameType
  currentGame: string
  onClick: (game: GameType) => void
}) => {
  const gameImageUrl = gamesImageUrl[game.gameId]

  // FIXME: 우리 도메인 S3 URL으로 수정
  const item = {
    gameImage: gameImageUrl.gameImage,
    gameLogo: gameImageUrl.gameLogoImage,
    gameName: game.gameName,
  }
    
  return (
    <GameListButton
      className='min-w-[164px]'
      item={item}
      onClick={() => onClick(game)}
      isClicked={currentGame === game.gameName} />
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
  console.log(games)

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
