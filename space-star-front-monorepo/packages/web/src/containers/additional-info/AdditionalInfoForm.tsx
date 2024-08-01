'use client'

import { useSearchParams } from 'next/navigation'

import { signIn } from 'next-auth/react'

import { ChangeEvent, useEffect, useState } from 'react'
import { useFormState } from 'react-dom'

import { Button, Checkbox, Input, Select } from '@packages/ui'

import { checkNickname } from '@/apis/auth-sign'
import { createUser } from '@/apis/createUser'
import { createNewProfile } from '@/apis/createProfile'
import { createProfileImage } from '@/apis/createProfileImage'

import CustomDatePicker from '@/components/DatePicker/DatePicker'
import styles from '@/components/sign/sign.module.css'
import Toast from '@/components/Toast/toast'

import { AuthType } from '@/types/AuthType'

/** 로그인으로 토큰 받아오기 */
async function getToken(email: string): Promise<AuthType | undefined> {
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

/** 기본 이미지를 프로필에 추가 */
const signUpWithImage = async (email: string) => {
  try {
    // api로 로그인 -> AccessToken 받아오기
    const user = await getToken(email)
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

/** 프로필 생성 */
const createProfile = async (email: string) => {
  try {
    // api로 로그인 -> AccessToken, profile 여부 받아오기
    const user = await getToken(email)
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

  const [toastMessage, setToastMessage] = useState<string>('')
  const [toastType, setToastType] = useState<'info' | 'warning' | 'error'>(
    'info',
  )

  /** 토스트 메시지 */
  const showToast = (message: string, type: 'info' | 'warning' | 'error') => {
    setToastMessage(message)
    setToastType(type)
  }

  /** 닉네임 수정 핸들러 */
  const handleChangeNickname = (e: ChangeEvent<HTMLInputElement>) => {
    setNickname(e.target.value)
    setIsAvailable(false)
    setToastMessage('')
  }

  /** 닉네임 중복검사 */
  const handleCheckNickname = async () => {
    const isNicknameAvailable = await checkNickname(nickname)
    // console.log(isNicknameAvailable)

    if (!isNicknameAvailable) {
      showToast('다시 시도해주세요.', 'error')
    } else if (!isNicknameAvailable?.exist) {
      // 사용 가능
      setIsAvailable(!isNicknameAvailable.exist)
      showToast(isNicknameAvailable.message, 'info')
    } else {
      // 이미 있음
      showToast(isNicknameAvailable.message, 'error')
    }
  }

  useEffect(() => {
    // 회원가입 성공
    if (state.status === 200) {
      signUpWithImage(email)
      createProfile(email)

      // 로그인 후 dashboard로 이동
      signIn('kakao', { redirect: true, callbackUrl: '/dashboard' })
    } else if (state.status !== 0) {
      showToast(state.message, 'error')
    }
  }, [state])

  return (
    <form action={formAction}>
      {toastMessage && (
        <Toast
          message={toastMessage}
          type={toastType}
          position="bottom"
          offsetY={130}
        />
      )}

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
