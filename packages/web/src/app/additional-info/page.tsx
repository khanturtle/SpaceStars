import { Button } from '@packages/ui'

import AdditionalInfoForm from '@/containers/Additional/AdditionalInfoForm'
import AdditionalLayout from '@/containers/Additional/AdditionalLayout'

export default function Page() {
  async function handleSubmit(formData: FormData) {
    'use server'

    console.log('폼 ㄱㄱ')
    console.log(formData)
  }

  return (
    <section className="w-[100vw] h-[100vh] fixed inset-0 flex items-center justify-center bg-[url('/images/BG.svg')] bg-cover bg-center bg-no-repeat overflow-hidden z-10">
      <AdditionalLayout
        title="SIGN UP"
        description={`회원가입을 위해\n 아래의 항목을 입력해주세요.`}
      >
        <form action={handleSubmit}>
          <AdditionalInfoForm />
          <Button
            type="submit"
            shape="oval"
            primary
            backgroundColor="#000"
            size="large"
            className="h-[60px] mt-2"
            label="회원가입"
          >
            회원가입
          </Button>
        </form>
      </AdditionalLayout>
    </section>
  )
}
