import Link from 'next/link'

import SignLayout from '@/containers/sign/SignLayout'

const SignInPage = () => {
  return (
    <section className="w-[100vw] h-[100vh] fixed inset-0 flex items-center justify-center bg-[url('/images/BG.svg')] bg-cover bg-center bg-no-repeat overflow-hidden z-10">
      <SignLayout
        className={`w-[90%] h-[90%] sm:w-[80%] sm:h-[80%] 
                  md:w-[60%] md:h-[60%] lg:w-[50%] lg:h-[60%]
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
    </section>
  )
}

export default SignInPage
