import {
  Badge,
  TeamCardJoinButton,
  TeamCardTitle,
  TeamCardUserAvatar,
} from '@packages/ui'
import TeamBox from './TeamBox'

type RoomType = {
  roomType: 'closed' | 'open'
  typeValue: '모집완료' | '모집중'
}

function getRoomTypeInfo(isFinished: boolean): RoomType {
  return {
    roomType: isFinished ? 'closed' : 'open',
    typeValue: isFinished ? '모집완료' : '모집중',
  }
}

export default function TeamListWrapper({
  searchParams,
  Teams,
}: {
  searchParams: { [key: string]: string }
  Teams: any[]
}) {
  const viewType = searchParams.view === 'list' ? 'list' : 'card'

  return (
    <TeamBox className="pt-[40px]">
      <TeamBox.TeamCardList type={viewType}>
        {Teams.map((team) => {
          const { roomType, typeValue } = getRoomTypeInfo(team.isFinished)

          return (
            <TeamBox.TeamCardItem
              key={team.index}
              type={viewType}
              isFinished={team.isFinished}
              gameData={
                <Badge
                  value={team.gameName}
                  type="game"
                  size={viewType === 'card' ? 'small' : 'large'}
                />
              }
              roomType={
                <Badge
                  value={`${typeValue} ${team.memberCount}/${team.maxLimit}`}
                  type={roomType}
                  size={viewType === 'card' ? 'small' : 'large'}
                />
              }
              users={
                <TeamCardUserAvatar
                  size={viewType === 'card' ? 'small' : 'medium'}
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
  )
}
