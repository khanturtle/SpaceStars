'use client'

import React from 'react'
import { CirclePlusIcon } from '@packages/ui'

type GameOptionType = {
  index: number
  position_id?: number
  position_name?: string
  position_name_kor?: string
  position_image?: string
}

export interface GameOptionProps {
  className?: string
  gameImage?: string
  gameName?: string
  options?: unknown[]
}

export default function GameOptionSelect({
  className,
  gameImage,
  gameName,
  options,
}: GameOptionProps) {
  return (
    <div
      className={`shadow-[0px_4px_4px_0px_rgba(0,0,0,0.25)] flex w-[335px] h-20 justify-between items-center shrink-0 border bg-[color:var(--White-50,#fff)] px-6 py-3.5 rounded-[15px] border-solid border-[#bb86fc] ${className}`}
    >
      <div className="relative w-[80px] max-h-[52px]">
        <img
          className="object-contain w-full h-full"
          src={gameImage}
          alt={gameName}
        />
      </div>

      <div className="flex items-start gap-2.5">
        {/* 해당 게임에 맞는 옵션 선택하기 */}
        {options &&
          options.map(() => (
            <button
              type="button"
              onClick={() => {}}
              className="w-[40px] h-[40px] relative"
            >
              <i>
                <CirclePlusIcon />
              </i>
            </button>
          ))}
      </div>
    </div>
  )
}
