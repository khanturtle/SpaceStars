import styles from './additional.module.css'

/** 서브 컴포넌트 */
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

export default function AdditionalLayout({
  className = `w-[90%] h-[90%] sm:w-[80%] sm:h-[80%] 
                  md:w-[60%] md:h-[60%] lg:w-[50%] lg:h-[60%]
                  flex flex-col items-center justify-around`,
  children,
  title,
  description,
}: {
  className?: string
  children?: React.ReactNode
  title?: string
  description?: string
}) {
  return (
    <div className={className}>
      <Legend title={title} description={description} />

      {children}
    </div>
  )
}

AdditionalLayout.Legend = Legend
