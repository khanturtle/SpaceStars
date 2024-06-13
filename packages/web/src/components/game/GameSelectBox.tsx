'use client'

import { useEffect, useState } from 'react'

import { CirclePlusIcon } from '@packages/ui'

import { getGameOptionDetail } from '@/apis/game'
import {
  Popover,
  PopoverContent,
  PopoverTrigger,
} from '@/components/ui/popover'

const SelectOption = ({
  gameId,
  option,
}: {
  gameId: number
  option: string
}) => {
  const [optionDetail, setOptionDetail] = useState([])
  useEffect(() => {
    const getOptionDetails = async () => {
      const details = await getGameOptionDetail(gameId, option)

      if (details) {
        setOptionDetail(details)
      }
    }
    getOptionDetails()
  }, [])

  return (
    <Popover>
      <PopoverTrigger className="w-[40px] h-[40px] relative">
        <i>
          <CirclePlusIcon />
        </i>
      </PopoverTrigger>
      <PopoverContent className="z-[9999]">
        <div className="flex">
          {optionDetail.map((item) => (
            <div key={item.index}>{item.index}</div>
          ))}
        </div>
      </PopoverContent>
    </Popover>
  )
}

export default function GameSelectBox({
  gameImage,
  gameName,
  gameId,
  options,
}: {
  gameImage: string
  gameName: string
  gameId: number
  options: string[]
}) {
  return (
    <div
      className={`shadow-[0px_4px_4px_0px_rgba(0,0,0,0.25)] flex w-[335px] h-20 justify-between items-center shrink-0 border bg-[color:var(--White-50,#fff)] px-6 py-3.5 rounded-[15px] border-solid border-[#bb86fc]`}
    >
      <div className="relative w-[80px] max-h-[52px]">
        <img
          className="object-contain w-full h-full"
          src={gameImage}
          alt={gameName}
        />
      </div>

      <div className="flex items-start gap-2.5">
        {options &&
          options.map((option) => (
            <SelectOption gameId={gameId} option={option} />
          ))}
      </div>
    </div>
  )
}
