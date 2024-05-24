import Link from 'next/link'

import SignLayout from 'packages/web/src/containers/sign/SignLayout'

import Modal from '../../../components/modal/modal'

export default function Page() {
  return (
    <Modal>
      <SignLayout
        className={`h-full py-[50px]
                  flex flex-col items-center justify-around`}
        type="sign-in"
      >
        <SignLayout.SignBox>
          <div className="flex">
            <p className="text-base not-italic font-normal leading-[170%] text-[color:var(--body-text-color,#333)]">
              Don&apos;t have an account?
            </p>
            <Link
              href="/sign-up"
              replace
              className="text-[#7d12ff] text-base not-italic font-bold leading-[170%] pl-1"
            >
              Sign Up.
            </Link>
          </div>
        </SignLayout.SignBox>
      </SignLayout>
    </Modal>
  )
}
