import RightSidebar from '@/components/Sidebar/RightSidebar'
import Sidebar from '@/components/Sidebar/Sidebar'

import { getServerSession } from 'next-auth/next'

import { options } from '@/app/api/auth/[...nextauth]/options'

export default async function layout({
  children,
}: {
  children: React.ReactNode
}) {
  const session = await getServerSession(options)

  console.log("여긴 대시보드 레이아웃", session)

  return (
    <main className="flex w-full h-[calc(100vh_-_100px)] overflow-hidden mx-auto my-0">
      <Sidebar />
      {children}
      <RightSidebar />
    </main>
  )
}
