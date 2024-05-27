'use client'

import { Button } from '@packages/ui'
import AdditionalInfoForm from './AdditionalInfoForm'

import SignLayout from './SignLayout'

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
      <form action="" onSubmit={onSubmit}>
        <AdditionalInfoForm />
        <Button
          type="submit"
          shape="oval"
          primary
          backgroundColor="#000"
          size="large"
          className="h-[60px] mt-2 w-full"
          label="회원가입"
          onClick={onSubmit}
        >
          회원가입
        </Button>
      </form>
    </div>
  )
}
