'use client'

import { useRouter } from 'next/navigation'

import { useContext, useEffect } from 'react'
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
import { useToast } from '@/components/Toast/toast-provider'

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

  const { showToast } = useToast()

  const initialState = {
    code: 0,
    message: '',
    result: { roomNumber: '' },
  }

  const [state, formAction] = useFormState(joinTeamForm, initialState)

  const handleToast = (message: string, type: 'info' | 'error') => {
    showToast({
      message: message,
      type: type,
      position: 'bottom',
    })
  }

  useEffect(() => {
    if (state.code === 0) {
      return
    }
    // 비번 맞?
    if (state.code === 200) {
      closeModal()
      router.push(`/dashboard/chat/group/${item.roomNumber}`)
    } else {
      // 비번 틀림
      handleToast(state.message, 'error')
      closeModal()
    }

    return () => {}
  }, [state])

  async function handleJoin() {
    if (item.isFinished) {
      handleToast('모집 완료된 방입니다.', 'error')
      return
    }
    // TODO: 내가 속한 방인지 확인하는 API 요청
    const isJoined = false
    // 내가 속한 방이면, 바로 참가
    if (isJoined) {
      router.push(`/dashboard/chat/group/${item.roomNumber}`)
      return
    }

    // 내가 속한 방이 아니면, 참가 시도
    // 비번 있으면, 입력 후 방 참가
    if (item.isPassword) {
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
            <form
              action={formAction}
              className="flex flex-col items-center justify-center"
            >
              <input type="hidden" name="roomNumber" value={item.roomNumber} />
              <input type="hidden" name="isPassword" value={item.isPassword} />
              <Input
                id="password"
                label="비밀번호"
                className="relative w-full mb-4"
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
    }
    // 비번이 없으면, 채팅방 참가
    else {
      const res = await joinTeam(item.roomNumber)
      if (res.result === null) {
        handleToast(res.message ?? '다시 시도해주세요', 'error')
      } else {
        router.push(`/dashboard/chat/group/${item.roomNumber}`)
      }
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
