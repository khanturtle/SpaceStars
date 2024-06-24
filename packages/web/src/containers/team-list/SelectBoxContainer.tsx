import { LockIcon } from '@packages/ui'

import { GameTypes } from '@/types/type'

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
    <div>
      <GameSelectBox games={games} searchParams={searchParams} />

      <div className="h-[20px]" />

      <SubSelectBox>
        <SubSelectBox.Title
          title="Team List"
          desc={`(${teamListCount} Items)`}
        />
        <div className="flex-1" />
        <SubSelectBox.Search />
        {/* FIXME: query key-value 수정 */}
        <SubSelectBox.SubButton
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
        />
        <SubSelectBox.ViewToggle searchParams={searchParams} />
      </SubSelectBox>
    </div>
  )
}
