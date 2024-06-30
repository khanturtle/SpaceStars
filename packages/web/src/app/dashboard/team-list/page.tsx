import { getTeamChatRooms } from '@/apis/getChat'
import { getGames } from '@/apis/getGame'

import SelectBoxContainer from '@/containers/team-list/SelectBoxContainer'
import TeamListWrapper from '@/containers/team-list/TeamListWrapper'
import CreateButton from '@/containers/team-list/CreateButton'
import { getTeamListData } from '@/lib/getTeamListData'

export default async function page({
  searchParams,
}: {
  searchParams: { [key: string]: string }
}) {
  const games = await getGames()

  const teamList = await getTeamChatRooms(searchParams)
  const teamListData = await getTeamListData(teamList)

  const teamListCount = teamList.length ?? 0

  return (
    <div className="flex-1 px-[50px] py-[42px] h-[full] overflow-auto">
      <SelectBoxContainer
        searchParams={searchParams}
        games={games}
        teamListCount={teamListCount}
      />

      <TeamListWrapper searchParams={searchParams} Teams={teamListData} />

      <CreateButton games={games} />
    </div>
  )
}
