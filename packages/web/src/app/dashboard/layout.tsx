import RightSidebar from '@/components/Sidebar/RightSidebar'
import Sidebar from '@/components/Sidebar/Sidebar'

export default async function layout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    <div className="relative flex w-full h-full">
      <Sidebar />
      {children}
      <RightSidebar />
    </div>
  )
}
