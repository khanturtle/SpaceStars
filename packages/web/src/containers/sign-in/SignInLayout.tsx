import Link from 'next/link'

import SignLayout from '@/components/sign/SignLayout'

export default function SignInLayout({ className }: { className?: string }) {
  return (
    <SignLayout className={className} type="sign-in">
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
  )
}
