'use client'

import { useEffect, useState } from 'react'

import { GameType, getGameOptions } from '@/apis/game'
import GameSelectBox from '@/components/game/GameSelectBox'
import { useGameStore } from '@/store/gameStore'

const GameOption = ({ item }: { item: GameType }) => {
  const [options, setOptions] = useState<string[]>([])

  useEffect(() => {
    const getIsOption = async () => {
      const isOptions = await getGameOptions(item.gameId)
      if (isOptions === undefined) {
        console.log('no options')
      } else {
        const optionList: string[] = []
        Object.entries(isOptions).forEach(([key, value]) => {
          if (value) {
            optionList.push(key)
          }
        })
        setOptions(optionList)
      }
    }

    getIsOption()
  })

  return (
    <GameSelectBox
      gameImage={item.gameLogoImage}
      gameName={item.gameNameKor}
      gameId={item.gameId}
      options={options}
    />
  )
}

export default function AdditionalOptions() {
  const { selectedGames, selectedGamesCount, addGame, removeGame } =
    useGameStore()

  return (
    <div className="flex flex-col items-center gap-[14px] mb-[47px]">
      {selectedGames.map((item) => (
        <GameOption key={item.index} item={item} />
      ))}
    </div>
  )
}
