import Teams from './state'

import { getGames } from '@/apis/game'
import SelectBoxContainer from '@/containers/team-list/SelectBoxContainer'
import TeamListWrapper from '@/containers/team-list/TeamListWrapper'

// TODO: 팀 리스트 받아오기

export default async function page({
  searchParams,
}: {
  searchParams: { [key: string]: string }
}) {
  const games = await getGames()

  return (
    <section className="flex-1 px-[50px] py-[42px] h-[full] overflow-auto">
      <SelectBoxContainer searchParams={searchParams} games={games} />

      <TeamListWrapper searchParams={searchParams} Teams={Teams} />
    </section>
  )
}
