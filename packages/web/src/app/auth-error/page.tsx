import { redirect } from 'next/navigation'

async function checkError(errorParams: string) {
  const error = errorParams

  if (error === 'UserSignUpRequired') {
    redirect('/additional-info')
  }
}

export default async function ErrorPage({
  searchParams,
}: {
  searchParams: { [key: string]: string }
}) {
  await checkError(searchParams.error)

  return (
    <div>
      <p>처리 중 에러가 발생했습니다.</p>
    </div>
  )
}
