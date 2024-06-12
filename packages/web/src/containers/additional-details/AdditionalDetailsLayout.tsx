import AdditionalDetailsContainer from "./AdditionalDetailsContainer"

// TODO: 다음 버튼 유효성 검증 및 disabled
export default function AdditionalDetailsLayout({
  className,
  step,
}: {
  className?: string
  step: string
}) {
  return (
    <div className={className}>
    <AdditionalDetailsContainer  step={step} />
    </div>
  )
}
