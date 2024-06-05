import styles from './sign.module.css'
import Button from './SignButton'

interface ContentType {
  imageUrl: string
  title: string
  description: string
  label: string
}

interface TypeContents {
  'sign-in': ContentType
  'sign-up': ContentType
}

const typeContents: TypeContents = {
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
  image,
}: {
  type?: 'sign-in' | 'sign-up'
  title?: string
  description?: string
  image: React.ReactNode
}) => {
  // FIXME: type이 안들어올 때 체크
  const typeContent = typeContents[type!]

  return (
    <div className={styles['title-container']}>
      {image && <div className="h-[165px] aspect-[1] relative">{image}</div>}
      {title && <h3>{title}</h3>}
      {description && <p>{description}</p>}
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
    <section className={className}>
      {/* {type && (
        <>
          <Legend type={type} />
          <Button type={type} />
        </>
      )} */}
      {children}
    </section>
  )
}

SignLayout.Legend = Legend
SignLayout.Button = Button
SignLayout.SignBox = SignBox

export default SignLayout
