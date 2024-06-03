'use client'

import { useSearchParams } from 'next/navigation'

import { ChangeEvent, useState } from 'react'

import { Button, Checkbox, Input, Select } from '@packages/ui'
import { signIn, useSession } from 'next-auth/react'

import createUser from '@/apis/createUser'
import CustomDatePicker from '@/components/DatePicker/DatePicker'
import styles from '@/components/sign/sign.module.css'

// MALE,FEMALE,OTHER
const genderOptions = [
  { value: 'MALE', label: '남자' },
  { value: 'FEMALE', label: '여자' },
  { value: 'OTHER', label: '비공개' },
]

/** 닉네임 중복검사 */
const NicknameInput = ({
  value,
  onChange,
}: {
  value: string
  onChange: (e: ChangeEvent<HTMLInputElement>) => void
}) => {
  const handleClick = () => {
    console.log('닉네임 중복 검사', value)
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

export default function AdditionalInfoForm() {
  const { data, status } = useSession()
  console.log(data, status)

  const query = useSearchParams()

  const imageUrl = query.get('profileImage') || ''
  const email = query.get('email') || ''
  const [nickname, setNickname] = useState(query.get('nickname') || '')
  const [gender, setGender] = useState('')
  const [birth, setBirth] = useState<Date | undefined>(new Date())
  const [isChecked, setIsChecked] = useState(false)

  const handleSubmit = async () => {
    const formData = {
      email,
      nickname,
      imageUrl,
      gender,
      birth: birth!.toISOString().slice(0, 10),
      infoAgree: isChecked,
    }

    const success = await createUser(formData)

    if (success) {
      // 회원가입 성공 후 로그인
      signIn('kakao', { redirect: true, callbackUrl: '/' })
    } else {
      // 에러 처리
    }
  }

  return (
    <form action={handleSubmit}>
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
          selectedOption={gender}
          onChange={(value: string) => setGender(value)}
        />

        <CustomDatePicker id="birth" date={birth} setDate={setBirth} />

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

      <Button
        type="submit"
        shape="oval"
        primary
        backgroundColor="#000"
        size="full"
        className="h-[60px] mt-2"
        label="회원가입"
      />
    </form>
  )
}
