/* eslint-disable @typescript-eslint/no-unused-vars */
/* eslint-disable no-unused-vars */

'use client'

import { ChangeEvent, useEffect, useState } from 'react'

import { Button, Checkbox, Input, Select } from '@packages/ui'

import styles from '../sign/sign.module.css'

import DatePickerCustom from '@/components/DatePicker/DatePicker'

const genderOptions = [
  { value: 'male', label: '남자' },
  { value: 'female', label: '여자' },
]

const NicknameInput = ({
  value,
  onChange,
}: {
  value: string
  onChange: (e: ChangeEvent<HTMLInputElement>) => void
}) => {
  const handleClick = () => {
    console.log('닉네임 중복 검사')
  }

  return (
    <div className={`${styles['input-box']} ${styles.nickname}`}>
      <Input
        id="nickname"
        label="닉네임"
        required
        className={styles['input-box']}
        value={value}
        onChange={onChange}
      />
      <Button
        label="중복검사"
        size="medium"
        backgroundColor="#202020"
        primary
        className="h-[53px]"
        onClick={handleClick}
      />
    </div>
  )
}

const AdditionalInfoForm = ({ children }: { children?: React.ReactNode }) => {
  const [email] = useState('')
  const [nickname, setNickname] = useState('')
  const [gender, setGender] = useState('')
  const [birthday, setBirthday] = useState('')
  const [isChecked, setIsChecked] = useState(false)

  useEffect(() => {
    console.log(gender)
  }, [])

  return (
    <div className={styles['form-wrapper']}>
      <Input
        id="email"
        label="이메일"
        disabled
        className={styles['input-box']}
        value={email}
      />

      <NicknameInput
        value={nickname}
        onChange={(e: ChangeEvent<HTMLInputElement>) =>
          setNickname(e.target.value)
        }
      />

      <Select
        className={`${styles['input-box']} z-10`}
        id="gender"
        label="성별"
        options={genderOptions}
        // selectedOption={gender}
        // onChange={(g: string) => setGender(g)}
      />

      <DatePickerCustom className={styles['input-box']} id="birthday" />

      <Checkbox
        className={styles['input-check']}
        id="agree"
        text="개인정보 수집동의"
        isChecked={isChecked}
        onChange={(e: ChangeEvent<HTMLInputElement>) =>
          setIsChecked(e.target.checked)
        }
      />
    </div>
  )
}

// AdditionalInfoForm.EmailInput = EmailInput
// AdditionalInfoForm.NicknameInput = NicknameInput
// AdditionalInfoForm.GenderSelector = GenderSelector

export default AdditionalInfoForm
