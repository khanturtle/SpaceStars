'use client'

import { useRouter } from 'next/navigation'

import { useContext, useEffect, useReducer, useState } from 'react'
import { useFormState } from 'react-dom'

import { PlusIcon } from 'lucide-react'

import { Button, Checkbox, Input } from '@packages/ui'

import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from '@/components/ui/select'

import FormLayout from '@/components/form/formLayout'
import { ModalContext } from '@/components/providers/modal-provider'

import { createTeam } from '@/apis/createChat'
import { GameTypes } from '@/types/type'

import styles from './teamList.module.css'

const CreateTeamForm = ({ games }: { games: GameTypes[] }) => {
  const router = useRouter()

  const { closeModal } = useContext(ModalContext)

  const [isChecked, setIsChecked] = useReducer((state) => {
    return !state
  }, false)

  const [selectGame, setSelectGame] = useState<string | number>('')
  const [selectMember, setSelectMember] = useState<string | number>('')

  const initialState = {
    code: 0,
    message: '',
    result: { roomNumber: '' },
  }

  const [state, formAction] = useFormState(createTeam, initialState)

  useEffect(() => {
    if (state.code === 200) {
      closeModal()
      router.push(`/dashboard/chat/group/${state.result.roomNumber}`)
    }
  }, [state])

  return (
    <form action={formAction} className={styles.form}>
      <Input
        id="roomName"
        label="모집 팀명"
        required
        className={styles['name-input']}
      />

      <div className={styles['input-group']}>
        <input type="hidden" name="gameId" value={selectGame} />
        <Select
          value={selectGame?.toString()}
          onValueChange={(v: string) => setSelectGame(v)}
        >
          <SelectTrigger
            className="w-[215px] h-[60px] border border-[color:var(--White-50,#fff)] shadow-[0px_4px_10px_0px_rgba(37,73,150,0.1)] rounded-[10px] border-solid text-[color:var(--secondary-text-color,#666)] text-base not-italic font-normal leading-[170%] "
            style={{ background: 'rgba(255, 255, 255, 0.5)' }}
          >
            <SelectValue placeholder="게임" />
          </SelectTrigger>

          <SelectContent className="relative z-[9999] w-full h-[230px] bg-[white] border-none ">
            {games &&
              games.map((game) => (
                <SelectItem
                  key={game.gameId}
                  value={game.gameId.toString()}
                  className="text-base not-italic font-normal leading-[170%] bg-[none] m-0"
                >
                  {game.gameNameKor}
                </SelectItem>
              ))}
          </SelectContent>
        </Select>

        <input type="hidden" name="maxMembers" value={selectMember} />
        <Select
          value={selectMember?.toString()}
          onValueChange={(v: string) => setSelectMember(v)}
        >
          <SelectTrigger
            className="w-[112px] h-[60px] border border-[color:var(--White-50,#fff)] shadow-[0px_4px_10px_0px_rgba(37,73,150,0.1)] rounded-[10px] border-solid text-[color:var(--secondary-text-color,#666)] text-base not-italic font-normal leading-[170%] "
            style={{ background: 'rgba(255, 255, 255, 0.5)' }}
          >
            <SelectValue placeholder="인원" />
          </SelectTrigger>

          <SelectContent className="relative z-[9999] w-full h-[230px] bg-[white] border-none ">
            {Array.from({ length: 5 }, (_, i) => (
              <SelectItem
                key={i + 1}
                value={(i + 1).toString()}
                className="text-base not-italic font-normal leading-[170%] bg-[none] m-0"
              >
                {i + 1}
              </SelectItem>
            ))}
          </SelectContent>
        </Select>
      </div>

      <Checkbox
        className={styles['checkbox-input']}
        id="isPassword"
        text="비밀방"
        isChecked={isChecked}
        onChange={() => setIsChecked()}
      />
      {isChecked && (
        <Input
          id="password"
          label="비밀번호"
          className={styles['password-input']}
        />
      )}

      <Input
        id="memo"
        label="메모"
        isChecked={false}
        className={`${styles['memo-input']} ${isChecked ? 'h-[66px]' : 'h-[133px]'}`}
      />

      <Button
        className={styles.button}
        type="submit"
        primary
        size="full"
        backgroundColor="var(--btn-color, #000)"
        label="팀 등록"
        shape="rounded"
      />
    </form>
  )
}

export default function CreateButton({ games }: { games: GameTypes[] }) {
  const { openModal } = useContext(ModalContext)

  const handleClick = () => {
    openModal(
      <div className={`relative h-full flex flex-col items-center`}>
        <FormLayout
          className="relative h-full px-[204px] pt-[90px] pb-[85px]
                  flex flex-col items-center"
        >
          <FormLayout.Legend
            title="Create Team"
            description={`팀 생성을 위해\n 아래의 항목을 입력해주세요.`}
          />
          <CreateTeamForm games={games} />
        </FormLayout>
      </div>,
    )
  }

  return (
    <button
      type="button"
      onClick={handleClick}
      className={styles['create-button']}
    >
      <PlusIcon strokeWidth={2.5} />
    </button>
  )
}
