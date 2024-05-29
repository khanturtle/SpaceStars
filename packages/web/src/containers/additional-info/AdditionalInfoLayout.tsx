import AdditionalInfoForm from './AdditionalInfoForm'

import FormLayout from '@/components/form/FormLayout'

export default function AdditionalInfoLayout({
  className,
}: {
  className?: string
}) {
  return (
    <FormLayout className={` ${className}`}>
      <FormLayout.Legend
        title="SIGN UP"
        description={`회원가입을 위해\n 아래의 항목을 입력해주세요.`}
      />

      <AdditionalInfoForm />
    </FormLayout>
  )
}
