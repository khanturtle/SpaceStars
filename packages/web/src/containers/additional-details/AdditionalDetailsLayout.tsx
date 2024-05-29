'use client'

import { useRouter } from 'next/navigation'

import FormLayout from '@/components/form/formLayout'

export default function AdditionalDetailsLayout({
  className,
  step,
}: {
  className?: string
  step: string
}) {
  const router = useRouter()

  const handleNextStep = () => {
    const nextStep = Number(step) + 1
    if (nextStep < 3) {
      router.push(`/additional-details/${nextStep}`)
    } else {
      console.log('끝')
    }
  }
  const handlePrevStep = () => {}

  return (
    <FormLayout className={`${className} w-[335px]`}>
      <FormLayout.Legend
        title="SIGN UP?"
        description={`좋아하는 게임을\n 선택해주세요 (최대 3개)`}
      />
      <section className="border h-36">{step} 폼쓰</section>

      <FormLayout.PrevNextButton
        onPrevClick={handlePrevStep}
        onNextClick={handleNextStep}
      />

      <FormLayout.NextButton onClick={handleNextStep} />
      <FormLayout.PassButton />
    </FormLayout>
  )
}
