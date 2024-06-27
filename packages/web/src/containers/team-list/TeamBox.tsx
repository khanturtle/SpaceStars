'use client'

import { useRouter } from 'next/navigation'

import { useContext, useEffect, useState } from 'react'
import { useFormState } from 'react-dom'

import {
  Badge,
  Button,
  Input,
  TeamCardJoinButton,
  TeamCardTitle,
} from '@packages/ui'

import { joinTeam, joinTeamForm } from '@/apis/createChat'
import { ModalContext } from '@/components/providers/modal-provider'
import FormLayout from '@/components/form/formLayout'
import { defaultImage } from '@/store/defaultState'

import styles from './teamList.module.css'

const TeamBox = ({
  children,
  className,
}: {
  children?: React.ReactNode
  className?: string
}) => {
  return <section className={className}>{children}</section>
}

const TeamCardList = ({
  items,
  type = 'list',
}: {
  items?: any[]
  type?: 'card' | 'list'
}) => {
  const className =
    type === 'card' ? styles['card-container'] : styles['list-container']

  return (
    <ul className={className}>
      {items &&
        items.map((item) => (
          <TeamCardItem key={item.index} item={item} type={type} />
        ))}
    </ul>
  )
}

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

const TeamCardItem = ({
  item,
  type = 'list',
}: {
  item?: any
  type?: 'card' | 'list'
}) => {
  const TYPE = item.isFinished ? 'finished' : 'ongoing'
  const isCardVariant = type === 'card'
  const { roomType, typeValue } = getRoomTypeInfo(item.isFinished)

  const router = useRouter()
  const { openModal, closeModal } = useContext(ModalContext)

  const initialState = {
    code: 0,
    message: '',
    result: { roomNumber: '' },
  }

  const [state, formAction] = useFormState(joinTeamForm, initialState)

  useEffect(() => {
    if (state.code === 200) {
      closeModal()
      router.push(`/dashboard/chat/group/${item.roomNumber}`)
    } else {
      // TODO: 비번 틀림
      // alert(state.message)
    }

    return () => {}
  }, [state])

  // TODO: 비번 입력 폼 => 비번이 같으면 입장 아니면 모달닫고, 에러 표시
  const handleJoin = () => {
    if (item.isFinished) {
      alert('꽉 찬 방')
    } else if (item.isPassword) {
      // 비번 입력 후 방 참가
      openModal(
        <div className={`relative h-full flex flex-col items-center`}>
          <FormLayout
            className="relative h-full px-[204px] pt-[90px] pb-[85px]
                  flex flex-col items-center"
          >
            <FormLayout.Legend
              title="Join Team"
              description={`비밀번호를 입력해주세요.`}
            />
            <form action={formAction}>
              <input type="hidden" name="roomNumber" value={item.roomNumber} />
              <input type="hidden" name="isPassword" value={item.isPassword} />
              <Input
                id="password"
                label="비밀번호"
                className="relative w-[335px] mb-4"
              />
              <Button
                type="submit"
                primary
                size="full"
                backgroundColor="var(--btn-color, #000)"
                label="참가하기"
                shape="rounded"
              />
            </form>
          </FormLayout>
        </div>,
      )
    } else {
      joinTeam(item.roomNumber)
      router.push(`/dashboard/chat/group/${item.roomNumber}`)
    }
  }

  // const users={
  //   <TeamCardUserAvatar
  //     size={viewType === 'card' ? 'small' : 'medium'}
  //     users={team.memberList}
  //   />
  // }

  if (isCardVariant)
    return (
      <li className={`${styles.card} ${styles[TYPE]}`}>
        <div className="relative flex flex-row items-center w-full">
          <div className="flex gap-[5px]">
            <Badge
              value={item.gameData?.gameName ?? ''}
              type="game"
              size="small"
            />
            <Badge
              value={`${typeValue} ${item.currentMembers}/${item.maxMembers}`}
              type={roomType}
              size="small"
            />
          </div>
          {/* {users} */}
        </div>
        <TeamCardTitle
          className={styles.info}
          imageUrl={item.gameData?.gameImage ?? defaultImage}
          title={item.roomName}
          description={item.memo}
        />

        <div className={styles.cardButton}>
          <TeamCardJoinButton
            onClick={handleJoin}
            isLocked={item.isPassword}
            isFinished={item.isFinished}
            iconFill="var(--button-secondary-text)"
            className={styles['join-button']}
          />
        </div>
      </li>
    )

  return (
    <li className={`${styles.list} ${styles[TYPE]}`}>
      <div className="w-[380px]">
        <TeamCardTitle
          className={styles.info}
          imageUrl={item.gameData?.gameImage ?? defaultImage}
          title={item.roomName}
          description={item.memo}
        />
      </div>
      <div className="flex gap-[5px] flex-1">
        <Badge value={item.gameData?.gameName ?? ''} type="game" size="large" />
        <Badge
          value={`${typeValue} ${item.currentMembers}/${item.maxMembers}`}
          type={roomType}
          size="large"
        />
      </div>

      <div className="relative flex items-center justify-between w-[228px]">
        {/* <div className="relative">{users}</div> */}
        <div>
          <TeamCardJoinButton
            onClick={handleJoin}
            isLocked={item.isPassword}
            isFinished={item.isFinished}
            iconFill="var(--button-secondary-text)"
            className={styles['join-button']}
          />
        </div>
      </div>
    </li>
  )
}

TeamBox.TeamCardList = TeamCardList
TeamBox.TeamCardItem = TeamCardItem

export default TeamBox
