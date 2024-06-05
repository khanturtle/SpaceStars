import Image from 'next/image'

import { BotIcon } from '@packages/ui'

import SignLayout from '@/components/sign/SignLayout'

export default function SignUpLayout({ className }: { className?: string }) {
  return (
    <SignLayout className={className}>
      <SignLayout.Legend
        image={
          <Image
            alt="sign-in"
            src="/images/emoji/emoji_svg_1.svg"
            fill
            sizes="(max-width: 768px) 100vw, (max-width: 1200px) 50vw, 33vw"
            placeholder="blur"
            blurDataURL="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAoAAAAKCAYAAACNMs+9AAAAFklEQVR42mN8//HLfwYiAOOoQvoqBABbWyZJf74GZgAAAABJRU5ErkJggg=="
          />
        }
        title="Sign Up"
        description={'우주별에서 \n게임 메이트를 찾아보세요!'}
      />
      <SignLayout.Button label="카카오 회원가입" />
      <SignLayout.SignBox>
        <div className="flex">
          <p className="text-base not-italic font-bold leading-[170%] text-[#7d12ff]">
            도움이 필요하신가요?
          </p>
          <i className="flex items-center justify-center pl-1">
            <BotIcon />
          </i>
        </div>
      </SignLayout.SignBox>
    </SignLayout>
  )
}
