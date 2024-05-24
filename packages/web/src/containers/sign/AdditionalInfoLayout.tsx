'use client'

import { Button } from '@packages/ui'

import styles from './sign.module.css'
import SignLayout from './SignLayout'
//
export default function AdditionalInfoLayout({
  className = `w-[90%] h-[90%] sm:w-[80%] sm:h-[80%] 
                  md:w-[60%] md:h-[60%] lg:w-[50%] lg:h-[60%]
                  flex flex-col items-center justify-around`,
}: {
  className?: string
}) {
  const onSubmit = () => {
    console.log('폼 ㄱㄱ')
  }

  return (
    <div className={className}>
      <SignLayout>
        <SignLayout.Legend
          title="SIGN UP"
          description={`회원가입을 위해 \n아래의 항목을 입력해 주세요.`}
        />
      </SignLayout>

      {/* TODO: 폼 컴포넌트 쪼개기 */}
      <form
        action=""
        className={`${styles['form-wrapper']} `}
        onSubmit={onSubmit}
      >
        <div className={styles['input-wrapper']}>
          <div className={styles['input-box']}>이메일</div>
          <div className={`${styles['input-box']} ${styles.nickname}`}>
            <input type="text" />
            <Button label="중복검사" size="small" primary className="h-[53px]">
              ㅇㅅㅇ
            </Button>
          </div>
          <div className={styles['input-box']}>Input</div>
          <div className={styles['input-box']}>Input</div>
          <div className={styles['input-check']}>check</div>
        </div>

        <Button
          type="submit"
          shape="oval"
          primary
          backgroundColor="#000"
          size="large"
          className="h-[60px]"
          label="회원가입"
          onClick={onSubmit}
        >
          회원가입
        </Button>
      </form>
    </div>
  )
}
