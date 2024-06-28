import { getServerSession } from 'next-auth/next'
import { options } from './api/auth/[...nextauth]/options'
import GoToDashboard from '@/components/FloatButton/GoToDashboard'

export default async function Page() {
  const session = await getServerSession(options)

  return (
    <main className="bg-[color:var(--background-color)] h-full">
      <h3>랜딩페이지</h3>
      <div className="h-[1000px]"></div>
      <GoToDashboard session={session} />
    </main>
  )
}
