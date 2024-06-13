import { Button } from '@packages/ui'

import styles from './form.module.css'

export default function FormLayout({
  className,
  children,
}: {
  className?: string
  children?: React.ReactNode
}) {
  return <section className={className}>{children}</section>
}

const Legend = ({
  title,
  description,
}: {
  title?: string
  description?: string
}) => {
  return (
    <div className={`${styles['title-container']}`}>
      {title && <h3>{title}</h3>}
      {description && <p>{description}</p>}
    </div>
  )
}

const NextButton = ({ onClick }: { onClick?: () => void }) => {
  return (
    <Button
      label="다음"
      size="full"
      shape="oval"
      primary
      backgroundColor="#000"
      onClick={onClick}
    />
  )
}

const PrevNextButton = ({
  onPrevClick,
  onNextClick,
}: {
  onPrevClick?: () => void
  onNextClick?: () => void
}) => {
  return (
    <div className="flex gap-[15px] w-full">
      <Button
        className="flex-grow border border-solid border-[#7d12ff]"
        label="이전"
        shape="oval"
        primary
        backgroundColor="#BB86FC"
        onClick={onPrevClick}
      />
      <Button
        className="flex-grow"
        label="다음"
        shape="oval"
        primary
        backgroundColor="#000"
        onClick={onNextClick}
      />
    </div>
  )
}

FormLayout.Legend = Legend
FormLayout.NextButton = NextButton
FormLayout.PrevNextButton = PrevNextButton
