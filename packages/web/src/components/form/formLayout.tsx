import styles from './form.module.css'

const Legend = ({
  title,
  description,
}: {
  title?: string
  description?: string
}) => {
  return (
    <div className={`${styles['title-container']}`}>
      <h3>{title}</h3>
      <p>{description}</p>
    </div>
  )
}

export default function FormLayout({
  className,
  children,
}: {
  className?: string
  children?: React.ReactNode
}) {
  return <section className={className}>{children}</section>
}

FormLayout.Legend = Legend
