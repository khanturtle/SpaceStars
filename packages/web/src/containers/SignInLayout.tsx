import { KakaoButton } from '@packages/ui'

import DescriptionBox from '../components/sign/DescriptionBox'
import TitleContainer from '../components/sign/TitleContainer'

export default function SignInLayout() {
  return (
    <>
      <TitleContainer type="signIn" />
      <div className="h-[37px]" />
      <KakaoButton label="카카오 로그인" />

      <div className="h-[27px]" />
      <DescriptionBox
        text="Don't have an account?"
        link="/sign-up"
        linkName="Sign Up."
      />
    </>
  )
}
