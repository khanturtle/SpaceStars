import Image from 'next/image'
import Link from 'next/link'

import SignLayout from '@/components/sign/SignLayout'

export default function SignInLayout({ className }: { className?: string }) {
  return (
    <SignLayout className={className} type="sign-in">
      <SignLayout.Legend
        image={
          <Image
            alt="sign-in"
            src="/images/emoji/emoji_svg_3.svg"
            fill
            sizes="(max-width: 768px) 100vw, (max-width: 1200px) 50vw, 33vw"
            placeholder="blur"
            blurDataURL="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAoAAAAKCAYAAACNMs+9AAAAFklEQVR42mN8//HLfwYiAOOoQvoqBABbWyZJf74GZgAAAABJRU5ErkJggg=="
          />
        }
        title="Welcome Back!"
        description={'우주별에서 \n게임 메이트를 찾아보세요!'}
      />
      <SignLayout.Button label="카카오 로그인" />
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
