import RightSidebar from '@/components/Sidebar/RightSidebar'
import Sidebar from '@/components/Sidebar/Sidebar'

export default async function layout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    <main className="flex w-full h-[calc(100vh_-_100px)] overflow-hidden mx-auto my-0">
      <Sidebar />
      {children}
      <RightSidebar />
    </main>
  )
}
