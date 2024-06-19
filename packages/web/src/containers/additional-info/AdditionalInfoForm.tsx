'use client'

import { useSearchParams } from 'next/navigation'

import { signIn } from 'next-auth/react'

import { ChangeEvent, useEffect, useState } from 'react'
import { useFormState } from 'react-dom'

import { Button, Checkbox, Input, Select } from '@packages/ui'

import { checkNickname } from '@/apis/auth-sign'
import { createUser } from '@/apis/createUser'

import CustomDatePicker from '@/components/DatePicker/DatePicker'
import styles from '@/components/sign/sign.module.css'
import { createProfileImage } from '@/apis/createProfileImage'
import { AuthType } from '@/types/AuthType'
import { createNewProfile } from '@/apis/createProfile'

// MALE,FEMALE,OTHER
export const genderList = [
  { value: 'MALE', label: '남자' },
  { value: 'FEMALE', label: '여자' },
  { value: 'OTHER', label: '기타' },
]

/** 닉네임 입력창 */
const NicknameInput = ({
  value,
  onChange,
  onClick,
}: {
  value: string
  onChange: (e: ChangeEvent<HTMLInputElement>) => void
  onClick: () => void
}) => {
  return (
    <div className={`${styles['input-box']} ${styles.nickname}`}>
      <Input
        id="nickname"
        label="닉네임"
        required
        className={styles['input-box']}
        value={value}
        onChange={onChange}
        onBlur={onChange}
      />
      <Button
        label="중복검사"
        size="medium"
        backgroundColor="#202020"
        primary
        className="h-[53px]"
        onClick={onClick}
      />
    </div>
  )
}

// TODO: 유효성 검사: 닉네임, 성별 선택 여부, 생년월일 선택 여부, 개인정보 동의 여부
export default function AdditionalInfoForm() {
  const query = useSearchParams()

  const imageUrl = query.get('profileImage') || ''
  const email = query.get('email') || ''

  const [nickname, setNickname] = useState<string>(query.get('nickname') || '')
  const [isAvailable, setIsAvailable] = useState<boolean>(false)

  const [gender, setGender] = useState('')
  const [birth, setBirth] = useState<Date | undefined>(new Date())
  const [isChecked, setIsChecked] = useState(false)

  const initialState = {
    status: 0,
    message: '',
  }

  const createUserWithMore = createUser.bind(null, imageUrl, isAvailable)
  const [state, formAction] = useFormState(createUserWithMore, initialState)

  const handleChangeNickname = (e: ChangeEvent<HTMLInputElement>) => {
    setNickname(e.target.value)
    setIsAvailable(false)
  }

  /** 닉네임 중복검사 */
  const handleCheckNickname = async () => {
    const isNicknameAvailable = await checkNickname(nickname)
    setIsAvailable(!isNicknameAvailable.exist)
    // FIXME: alert 말고 다른 걸로 유효성 표시
    alert(isNicknameAvailable.message)
  }

  /** 로그인으로 토큰 받아오기 */
  async function getToken(): Promise<AuthType | undefined> {
    try {
      const res = await fetch(
        `${process.env.NEXT_PUBLIC_API_URL_V1}/auth/login`,
        {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            email: email,
          }),
        },
      ).then((r) => r.json())

      if (res.code === 200) {
        return res.result
      }
    } catch (err) {
      console.error('getToken error', err)
    }
  }

  /** 프로필 생성 */
  const createProfile = async () => {
    try {
      // api로 로그인 -> AccessToken, profile 여부 받아오기
      const user = await getToken()
      if (user) {
        const { accessToken, profile } = user

        // 프로필이 false이면, 프로필 생성 api 호출
        if (!profile) {
          createNewProfile(accessToken)
        }
      }
    } catch (err) {
      console.error('createProfile error', err)
    }
  }

  /** 기본 이미지를 프로필에 추가 */
  const signUpWithImage = async () => {
    try {
      // api로 로그인 -> AccessToken 받아오기
      const user = await getToken()
      if (user) {
        const { accessToken } = user

        // 기본 이미지를 메인 프로필로 지정하기
        const profile = {
          profileImageUrl:
            'https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/space-star-bucket/default-image.jpg',
          main: true,
        }
        createProfileImage(profile, accessToken)
      }
    } catch (err) {
      console.error('signUp error', err)
    }
  }

  useEffect(() => {
    // 회원가입 성공
    if (state.status === 200) {
      signUpWithImage()
      createProfile()

      // 로그인 후 dashboard로 이동
      signIn('kakao', { redirect: true, callbackUrl: '/dashboard' })
    } else if (state.status !== 0) {
      // FIXME: 유효성 에러 표시
      alert(state.message)
    }
  }, [state])

  return (
    <form action={formAction}>
      <div className={styles['form-wrapper']}>
        <Input
          id="email"
          label="이메일"
          disabled
          className={styles['input-box']}
          value={email}
        />
        <input type="hidden" name="email" value={email} />

        <NicknameInput
          value={nickname}
          onChange={handleChangeNickname}
          onClick={handleCheckNickname}
        />

        <Select
          className={`z-10 w-full h-[60px] rounded-[10px] text-[color:var(--secondary-text-color,#666)] text-base not-italic font-normal leading-[170%] ${styles['input-box']} ${styles.select}`}
          id="gender"
          label="성별"
          options={genderList}
          selectedOption={gender}
          onChange={(value: string) => setGender(value)}
        />

        <CustomDatePicker id="birth" date={birth} setDate={setBirth} />
        <input
          type="hidden"
          name="birth"
          value={birth!.toISOString().slice(0, 10)}
        />

        <Checkbox
          className={styles['input-check']}
          id="infoAgree"
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
