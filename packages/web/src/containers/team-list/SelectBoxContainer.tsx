import { GameTypes } from '@/types/type'
import { LockIcon } from '@packages/ui'
import GameSelectBox from './GameSelectBox'
import SubSelectBox from './SubSelectBox'

export default function SelectBoxContainer({
  searchParams,
  games,
}: {
  searchParams: { [key: string]: string }
  games: GameTypes[]
}) {
  return (
    <div>
      <GameSelectBox games={games} searchParams={searchParams} />

      <div className="h-[20px]" />

      <SubSelectBox>
        <SubSelectBox.Title
          title="Team List"
          // FIXME: Room 갯수로 수정
          desc={`(${games.length} Items)`}
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
