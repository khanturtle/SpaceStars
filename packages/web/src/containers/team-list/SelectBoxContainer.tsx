'use client'

// import { LockIcon } from '@packages/ui'
import { RotateCcwIcon } from 'lucide-react'

import Gutter from '@/components/Gutter'

import { GameTypes } from '@/types/type'
import { handleRefresh } from '@/lib/teamListAction'

import GameSelectBox from './GameSelectBox'
import SubSelectBox from './SubSelectBox'

export default function SelectBoxContainer({
  searchParams,
  games,
  teamListCount,
}: {
  searchParams: { [key: string]: string }
  games: GameTypes[]
  teamListCount: number
}) {
  return (
    <section>
      <GameSelectBox games={games} searchParams={searchParams} />

      <Gutter className="h-[20px]" />

      <SubSelectBox>
        <SubSelectBox.Title
          title="Team List"
          desc={`(${teamListCount} Items)`}
        />
        <Gutter className="flex-1" />

        {/* FIXME: query key-value 수정 */}

        {/* <SubSelectBox.SubButton
          name="모집중"
          queryKey="test1"
          queryValue="1"
          searchParams={searchParams}
        />
        <SubSelectBox.SubButton
          name="lock"
          icon={<LockIcon fill="#2C3149" />}
          queryKey="test2"
          queryValue="2"
          searchParams={searchParams}
        /> */}

        <SubSelectBox.ViewToggle searchParams={searchParams} />

        <SubSelectBox.SubButton
          name=""
          icon={<RotateCcwIcon stroke="#84818A" size={18} strokeWidth={2} />}
          onClick={() => handleRefresh()}
        />
      </SubSelectBox>
    </section>
  )
}
