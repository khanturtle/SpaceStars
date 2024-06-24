'use client'

import { useRouter } from 'next/navigation'

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

// TODO: CSS 수정
export default function TeamListWrapper({
  searchParams,
  Teams,
}: {
  searchParams: { [key: string]: string }
  Teams: any[]
}) {
  const router = useRouter()

  const viewType = searchParams.view === 'list' ? 'list' : 'card'
  return (
    <TeamBox className="pt-[40px]">
      <TeamBox.TeamCardList type={viewType}>
        {Teams.map((team) => {
          const { roomType, typeValue } = getRoomTypeInfo(team.isFinished)
          const handleJoin = () => {
            if (team.isFinished) {
              return
            } else if (team.isPassword) {
              console.log('비번확인')
            } else {
              console.log('enter room')
            }
          }
          return (
            <TeamBox.TeamCardItem
              key={team.index}
              type={viewType}
              isFinished={team.isFinished}
              gameData={
                <Badge
                  value={team.gameData.gameName}
                  type="game"
                  size={viewType === 'card' ? 'small' : 'large'}
                />
              }
              roomType={
                <Badge
                  value={`${typeValue} ${team.currentMembers}/${team.maxMembers}`}
                  type={roomType}
                  size={viewType === 'card' ? 'small' : 'large'}
                />
              }
              users={
                <TeamCardUserAvatar
                  size={viewType === 'card' ? 'small' : 'medium'}
                  users={team.memberList}
                />
              }
              title={
                <TeamCardTitle
                  className="w-[85%]"
                  imageUrl={team.gameData.gameImage}
                  title={team.roomName}
                  description={team.memo}
                />
              }
              joinButton={
                <TeamCardJoinButton
                  onClick={handleJoin}
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
