import SignLayout from 'packages/web/src/containers/sign/SignLayout'

import Modal from '../../../components/modal/modal'

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
          </div>
        </SignLayout.SignBox>
      </SignLayout>
    </Modal>
  )
}
