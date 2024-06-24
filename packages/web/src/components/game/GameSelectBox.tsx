'use client'

import Image from 'next/image'

import { useEffect, useState } from 'react'

import { CirclePlusIcon } from '@packages/ui'

import { GameOptionDetailType, getGameOptionDetail } from '@/apis/getGame'
import {
  Popover,
  PopoverContent,
  PopoverTrigger,
} from '@/components/ui/popover'
import { useSelectedOption } from '@/store/gameStore'

// FIXME: 최대 3개의 게임 중 1개만 옵션 선택 가능
const SelectOption = ({
  gameId,
  option,
}: {
  gameId: number
  option: 'isClass' | 'isPosition' | 'isServer' | 'isTier'
}) => {
  const [optionDetail, setOptionDetail] = useState<GameOptionDetailType[]>([])
  const [selectedOption, setSelectedOption] =
    useState<GameOptionDetailType | null>(null)

  const { setGameWithOption } = useSelectedOption()

  useEffect(() => {
    const getOptionDetails = async () => {
      const details = await getGameOptionDetail(gameId, option)

      if (details) {
        setOptionDetail(details)
      }
    }
    getOptionDetails()
  }, [])

  const handleOptionClick = (item: GameOptionDetailType) => {
    setSelectedOption(item)
    console.log(selectedOption)
    // console.log(gameId, option, item)
    setGameWithOption(gameId, option, item)
  }

  return (
    <Popover>
      <PopoverTrigger className="w-[40px] h-[40px] relative">
        {selectedOption ? (
          <>
            {selectedOption.image ? (
              <Image
                src={selectedOption.image}
                alt={selectedOption.name}
                fill
                sizes="(max-width: 768px) 100vw, (max-width: 1200px) 50vw, 33vw"
                style={{
                  objectFit: 'contain',
                }}
              />
            ) : (
              // FIXME: 이미지 없을 때
              <p>{selectedOption.nameKor}</p>
            )}
          </>
        ) : (
          <i>
            <CirclePlusIcon />
          </i>
        )}
      </PopoverTrigger>

      {/* TODO: 스크롤 CSS 수정? */}
      <PopoverContent
        className={`z-[9999] grid grid-cols-2 gap-2 w-[100%] max-h-[300px] overflow-y-auto`}
      >
        {optionDetail.map((item) => (
          <button
            key={item.index}
            type="button"
            className={`h-[30px] relative flex items-center space-x-2 px-3 py-2 
              hover:bg-gray-100 focus:bg-gray-200 focus:outline-none 
              rounded-md transition-colors duration-200
              ${selectedOption && selectedOption.id === item.id ? 'bg-gray-200' : ''}`}
            onClick={() => handleOptionClick(item)}
          >
            {item.image && (
              <Image src={item.image} alt={item.name} width={20} height={20} />
            )}
            <span className="text-gray-800">{item.nameKor}</span>
          </button>
        ))}
      </PopoverContent>
    </Popover>
  )
}

type OptionType = 'isClass' | 'isPosition' | 'isServer' | 'isTier'

export default function GameSelectBox({
  gameImage,
  gameName,
  gameId,
  options,
}: {
  gameImage: string
  gameName: string
  gameId: number
  options: OptionType[]
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
          options.map((option, _index) => (
            <SelectOption key={_index} gameId={gameId} option={option} />
          ))}
      </div>
    </div>
  )
}
