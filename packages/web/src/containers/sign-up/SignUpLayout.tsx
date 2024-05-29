import { BotIcon } from '@packages/ui'

import SignLayout from '@/components/sign/SignLayout'

export default function SignUpLayout({ className }: { className?: string }) {
  return (
    <SignLayout className={className} type="sign-up">
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
