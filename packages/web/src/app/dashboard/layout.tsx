import RightSidebar from '@/components/Sidebar/RightSidebar'
import Sidebar from '@/components/Sidebar/Sidebar'

export default async function layout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    // TODO: 여기를 메인으로 하고, 안에있는 페이지들은 section으로 만들기
    <div className="relative flex w-full h-full">
      <Sidebar />
      {children}
      <RightSidebar />
    </div>
  )
}
