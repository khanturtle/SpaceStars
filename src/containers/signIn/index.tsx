import KakaoButton from '@/components/buttons/kakaoButton'

import DescriptionBox from './DescriptionBox'
import TitleContainer from './TitleContainer'

export default function SignInLayout() {
  return (
    <>
      <TitleContainer type="signIn" />
      <div className="h-[37px]" />
      <KakaoButton />
      <div className="h-[27px]" />
      <DescriptionBox
        text="Don't have an account?"
        link="/sign-up"
        linkName="Sign Up."
      />
    </>
  )
}
