'use client'

import { useRouter } from 'next/navigation'

import {
  Badge,
  TeamCardJoinButton,
  TeamCardTitle,
  TeamCardUserAvatar,
} from '@packages/ui'

import TeamBox from './TeamBox'
import { useContext } from 'react'
import { ModalContext } from '@/components/providers/modal-provider'

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
  const { openModal } = useContext(ModalContext)

  const viewType = searchParams.view === 'list' ? 'list' : 'card'
  return (
    // FIXME: 컴포넌트 분리 ㄷㄷ
    <TeamBox className="pt-[40px]">
      <TeamBox.TeamCardList type={viewType}>
        {Teams.map((team) => {
          const { roomType, typeValue } = getRoomTypeInfo(team.isFinished)
          // TODO: 비번 입력 폼 => 비번이 같으면 입장 아니면 모달닫고, 에러 표시
          const handleJoin = () => {
            if (team.isFinished) {
              return
            } else if (team.isPassword) {
              // team.password
              openModal(
                <div
                  className="relative h-full px-[204px] pt-[90px] pb-[85px]
                  flex flex-col items-center"
                >
                  비번쳐
                </div>,
              )
            } else {
              router.push(`/dashboard/chat/group/${team.roomNumber}`)
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
