import styles from './sign.module.css'
import Button from './SignButton'

const Legend = ({
  title,
  description,
  image,
}: {
  title?: string
  description?: string
  image: React.ReactNode
}) => {
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
}: {
  children?: React.ReactNode
  className?: string
}) => {
  return <section className={className}>{children}</section>
}

SignLayout.Legend = Legend
SignLayout.Button = Button
SignLayout.SignBox = SignBox

export default SignLayout
