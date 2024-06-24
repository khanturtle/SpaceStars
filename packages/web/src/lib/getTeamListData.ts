import { getGameById } from '@/apis/getGame'
import { getBasicUserData } from './getUserData'

/** 팀 리스트 목록
 * gameId -> 게임 정보
 * memberUuidList -> 회원 정보
 */
export async function getTeamListData(teamList: any[]) {
  const teamListDetail = await Promise.all(
    teamList.map(async (team) => {
      const gameId = team.gameId
      const gameData = await getGameById(gameId)

      const memberUuidList = team.memberUuidList

      const memberDataPromises = memberUuidList.map(
        async (item: { memberUuid: string }) => {
          const memberData = await getBasicUserData(item.memberUuid)
          return { ...memberData, ...item }
        },
      )
      const memberDataList = await Promise.all(memberDataPromises)

      return { ...team, gameData, memberList: memberDataList }
    }),
  )

  return teamListDetail
}
