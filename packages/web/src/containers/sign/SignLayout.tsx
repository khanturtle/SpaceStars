import Image from 'next/image'

import styles from './sign.module.css'
import Button from './SignButton'

const typeContents = {
  'sign-in': {
    imageUrl: '/images/emoji/emoji_3.png',
    title: 'Welcome Back!',
    description: '우주별에서 \n게임 메이트를 찾아보세요!',
    label: '카카오 로그인',
  },
  'sign-up': {
    imageUrl: '/images/emoji/emoji_1.png',
    title: 'Sign Up',
    description: '우주별에서 \n게임 메이트를 찾아보세요!',
    label: '카카오 회원가입',
  },
}

const Legend = ({
  type,
  title,
  description,
}: {
  type?: 'sign-in' | 'sign-up'
  title?: string
  description?: string
}) => {
  const typeContent = typeContents[type!]

  return (
    <div className={styles['title-container']}>
      {type ? (
        <>
          <div className="h-[165px] aspect-[1] relative">
            <Image
              alt={typeContent.title}
              src={typeContent.imageUrl}
              fill
              sizes="(max-width: 768px) 100vw, (max-width: 1200px) 50vw, 33vw"
              placeholder="blur"
              blurDataURL="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAoAAAAKCAYAAACNMs+9AAAAFklEQVR42mN8//HLfwYiAOOoQvoqBABbWyZJf74GZgAAAABJRU5ErkJggg=="
            />
          </div>
          <h3>{typeContent.title}</h3>
          <p>{typeContent.description}</p>
        </>
      ) : (
        <>
          <h3>{title}</h3>
          <p>{description}</p>
        </>
      )}
    </div>
  )
}

const SignBox = ({ children }: { children: React.ReactNode }) => {
  // eslint-disable-next-line react/jsx-no-useless-fragment
  return <>{children}</>
}

const SignLayout = ({
  children,
  className,
  type,
}: {
  children?: React.ReactNode
  className?: string
  type?: 'sign-in' | 'sign-up'
}) => {
  return (
    <div className={className}>
      {type && (
        <>
          <Legend type={type} />
          <Button type={type} />
        </>
      )}
      {children}
    </div>
  )
}

SignLayout.Legend = Legend
SignLayout.Button = Button
SignLayout.SignBox = SignBox

export default SignLayout
