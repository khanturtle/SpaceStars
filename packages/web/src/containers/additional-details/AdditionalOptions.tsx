'use client'

import { useEffect, useState } from 'react'

import { GameType, getGameOptions } from '@/apis/game'
import GameSelectBox from '@/components/game/GameSelectBox'
import { useGameStore } from '@/store/gameStore'

type OptionType = 'isClass' | 'isPosition' | 'isServer' | 'isTier'

const GameOption = ({ item }: { item: GameType }) => {
  const [options, setOptions] = useState<OptionType[]>([])

  useEffect(() => {
    const getIsOption = async () => {
      const isOptions = await getGameOptions(item.gameId)
      if (isOptions === undefined) {
        console.log('no options')
      } else {
        const optionList: OptionType[] = []
        Object.entries(isOptions).forEach(([key, value]) => {
          if (value) {
            optionList.push(key as OptionType)
          }
        })
        setOptions(optionList)
      }
    }

    getIsOption()
  }, [])

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
  const { selectedGames } = useGameStore()

  return (
    <div className="flex flex-col items-center gap-[14px] mb-[47px]">
      {selectedGames.map((item) => (
        <GameOption key={item.index} item={item} />
      ))}
    </div>
  )
}
