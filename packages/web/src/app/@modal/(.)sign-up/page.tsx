import { BotIcon } from '@packages/ui'

import Modal from '@/components/modal/modal'
import SignLayout from '@/components/sign/SignLayout'

export default function Page() {
  return (
    <Modal>
      <SignLayout
        className={`h-full py-[50px]
                  flex flex-col items-center justify-around`}
        type="sign-up"
      >
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
    </Modal>
  )
}
