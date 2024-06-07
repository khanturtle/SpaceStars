import { LockIcon } from '@packages/ui'

import { getGames } from '@/apis/game'
import GameSelectBox from '@/containers/team-list/GameSelectBox'
import SubSelectBox from '@/containers/team-list/SubSelectBox'

// TODO: 팀 리스트 받아오기

type RoomType = {
  roomType: 'closed' | 'open'
  typeValue: '모집완료' | '모집중'
}

export default async function page({
  searchParams,
}: {
  searchParams: { [key: string]: string }
}) {
  function getRoomTypeInfo(isFinished: boolean): RoomType {
    return {
      roomType: isFinished ? 'closed' : 'open',
      typeValue: isFinished ? '모집완료' : '모집중',
    }
  }
  const games = await getGames()

  return (
    <>
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
      </SubSelectBox>
      {/* <div>
        <h2>카드</h2>
        <TeamBox className="">
          <TeamBox.TeamCardList type="card">
            {Teams.map((team) => {
              const { roomType, typeValue } = getRoomTypeInfo(team.isFinished)

              return (
                <TeamBox.TeamCardItem
                  key={team.index}
                  type="card"
                  isFinished={team.isFinished}
                  gameData={
                    <Badge value={team.gameName} type="game" size="small" />
                  }
                  roomType={
                    <Badge
                      value={`${typeValue} ${team.memberCount}/${team.maxLimit}`}
                      type={roomType}
                      size="small"
                    />
                  }
                  users={
                    <TeamCardUserAvatar
                      size="small"
                      users={[
                        {
                          profileImage: 'https://via.placeholder.com/52x52',
                          userId: 1,
                        },
                        {
                          profileImage: 'https://via.placeholder.com/1x1',
                          userId: 2,
                        },
                        {
                          profileImage: 'https://via.placeholder.com/1x1',
                          userId: 3,
                        },
                      ]}
                    />
                  }
                  title={
                    <TeamCardTitle
                      className="w-[85%]"
                      imageUrl="https://via.placeholder.com/52x52"
                      title={team.gameName}
                      description={team.memo}
                    />
                  }
                  joinButton={
                    <TeamCardJoinButton
                      isLocked={team.isPassword}
                      isFinished={team.isFinished}
                    />
                  }
                />
              )
            })}
          </TeamBox.TeamCardList>
        </TeamBox>
      </div>

      <div>
        <h2>리스트</h2>
        <TeamBox className="">
          <TeamBox.TeamCardList type="list">
            {Teams.map((team) => {
              const { roomType, typeValue } = getRoomTypeInfo(team.isFinished)

              return (
                <TeamBox.TeamCardItem
                  key={team.index}
                  type="list"
                  isFinished={team.isFinished}
                  gameData={
                    <Badge value={team.gameName} type="game" size="large" />
                  }
                  roomType={
                    <Badge
                      value={`${typeValue} ${team.memberCount}/${team.maxLimit}`}
                      type={roomType}
                      size="large"
                    />
                  }
                  users={
                    <TeamCardUserAvatar
                      className="w-[100px]"
                      size="medium"
                      users={[
                        {
                          profileImage: 'https://via.placeholder.com/52x52',
                          userId: 1,
                        },
                        {
                          profileImage: 'https://via.placeholder.com/1x1',
                          userId: 2,
                        },
                        {
                          profileImage: 'https://via.placeholder.com/1x1',
                          userId: 3,
                        },
                      ]}
                    />
                  }
                  title={
                    <TeamCardTitle
                      className="w-[250px]"
                      imageUrl="https://via.placeholder.com/52x52"
                      title={team.gameName}
                      description={team.memo}
                    />
                  }
                  joinButton={
                    <TeamCardJoinButton
                      isLocked={team.isPassword}
                      isFinished={team.isFinished}
                    />
                  }
                />
              )
            })}
          </TeamBox.TeamCardList>
        </TeamBox>
      </div> */}
    </>
  )
}
