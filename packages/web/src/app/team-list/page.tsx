import {
  Badge,
  TeamCardJoinButton,
  TeamCardTitle,
  TeamCardUserAvatar,
} from '@packages/ui'

import Teams from './state'

import TeamBox from '@/containers/team-list/TeamBox'

// TODO: 팀 리스트 받아오기

export default function page() {
  return (
    <div>
      <div>
        <h2>카드</h2>
        <TeamBox className="w-[1100px]">
          <TeamBox.TeamCardList>
            {Teams.map((team) => {
              const ROOM_TYPE = team.isFinished ? 'closed' : 'open'
              const TYPE_VALUE = team.isFinished ? '모집완료' : '모집중'

              return (
                <TeamBox.TeamCardItem
                  key={team.index}
                  isFinished={team.isFinished}
                  gameData={
                    <Badge value={team.gameName} type="game" size="small" />
                  }
                  roomType={
                    <Badge
                      value={`${TYPE_VALUE} ${team.memberCount}/${team.maxLimit}`}
                      type={ROOM_TYPE}
                      size="small"
                    />
                  }
                  users={
                    <TeamCardUserAvatar
                      size="small"
                      users={[
                        {
                          index: 1,
                          profileImage: 'https://via.placeholder.com/52x52',
                          userId: 1,
                        },
                        {
                          index: 2,
                          profileImage: 'https://via.placeholder.com/1x1',
                          userId: 2,
                        },
                        {
                          index: 3,
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
        <TeamBox>
          {Teams.map((team) => (
            <TeamBox.TeamList key={team.index} />
          ))}
        </TeamBox>
      </div>
    </div>
  )
}
