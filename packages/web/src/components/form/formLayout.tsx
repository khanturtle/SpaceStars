import Link from 'next/link'

import { ArrowIcon, Button } from '@packages/ui'

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

/** TODO: 모달 닫기 후 이전 페이지로 이동?? */
const PassButton = () => {
  return (
    <div className="flex justify-center w-full mt-6">
      <Link href="/" className="inline-flex items-center justify-center gap-8">
        <i>
          <ArrowIcon width="14" height="14" type="right" fill="white" />
        </i>
        <p className="text-white text-center text-base not-italic font-normal leading-[170%]">
          건너뛰기
        </p>
      </Link>
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
FormLayout.NextButton = NextButton
FormLayout.PrevNextButton = PrevNextButton
FormLayout.PassButton = PassButton
