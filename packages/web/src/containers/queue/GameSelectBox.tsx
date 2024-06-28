'use client'

import { useRouter } from 'next/navigation'

import { Dispatch, SetStateAction, useState } from 'react'

import { GameButton } from '@packages/ui'

import { useToast } from '@/components/Toast/toast-provider'
import { GameTypes } from '@/types/type'

import QueueButton from './QueueButton'

const GameItemButton = ({
  game,
  selectedGame,
  setSelectedGame,
}: {
  game: GameTypes
  selectedGame: string
  setSelectedGame: Dispatch<SetStateAction<string>>
}) => {
  const handleClick = () => {
    setSelectedGame(game.gameName)
  }

  return (
    <div>
      <GameButton
        item={{
          gameImage: game.gameLogoImage,
          gameName: game.gameNameKor,
        }}
        isClicked={selectedGame === game.gameName}
        onClick={handleClick}
      />
    </div>
  )
}

export default function GameSelectBox({ games }: { games: GameTypes[] }) {
  const [selectedGame, setSelectedGame] = useState<string>('')

  const router = useRouter()

  const { showToast } = useToast()

  const handleToast = (message: string) => {
    showToast({
      message: message,
      type: 'error',
      position: 'bottom',
    })
  }

  const handleStartQueue = () => {
    if (selectedGame === '') {
      handleToast('게임을 선택하세요!')
    } else {
      router.push(`/dashboard/queue/start?game=${selectedGame}`)
    }
  }

  return (
    <section className="flex flex-col">
      <div className="flex flex-wrap justify-start flex-row gap-[17px_30px] mt-[40px] mb-[47px]">
        {games &&
          games.map((game) => (
            <GameItemButton
              key={game.index}
              game={game}
              selectedGame={selectedGame}
              setSelectedGame={setSelectedGame}
            />
          ))}
      </div>

      <QueueButton>
        <QueueButton.MatchingButton onClick={handleStartQueue} />
      </QueueButton>
    </section>
  )
}
