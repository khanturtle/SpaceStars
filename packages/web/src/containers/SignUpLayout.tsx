import KakaoButton from '../components/buttons/kakaoButton'
import DescriptionBox from '../components/sign/DescriptionBox'
import TitleContainer from '../components/sign/TitleContainer'

export default function SignUpLayout() {
  return (
    <>
      <TitleContainer type="signUp" />
      <div className="h-[37px]" />
      <KakaoButton />
      <div className="h-[27px]" />
      <DescriptionBox text="" link="/" linkName="도움이 필요하신가요?" />
    </>
  )
}
