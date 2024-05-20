import Image from 'next/image'

import styles from './sign.module.css'

interface TypeDetails {
  imageUrl: string
  title: string
  description: string
}

const typeDetails: Record<'signIn' | 'signUp', TypeDetails> = {
  signIn: {
    imageUrl: '',
    title: 'Welcome Back!',
    description: '우주별에서 \n게임 메이트를 찾아보세요!',
  },
  signUp: {
    imageUrl: '',
    title: 'Sign Up',
    description: '우주별에서 \n게임 메이트를 찾아보세요!',
  },
}

interface TitleContainerProps {
  type: 'signIn' | 'signUp'
}

export default function TitleContainer({ type }: TitleContainerProps) {
  const { imageUrl, title, description } = typeDetails[type]

  return (
    <div className={styles['title-container']}>
      <div className="h-[165px] aspect-[1] relative">
        <Image
          alt={title}
          src={imageUrl}
          fill
          sizes="(max-width: 768px) 100vw, (max-width: 1200px) 50vw, 33vw"
          placeholder="blur"
          blurDataURL="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAoAAAAKCAYAAACNMs+9AAAAFklEQVR42mN8//HLfwYiAOOoQvoqBABbWyZJf74GZgAAAABJRU5ErkJggg=="
        />
      </div>
      <h3>{title}</h3>
      <p>{description}</p>
    </div>
  )
}
