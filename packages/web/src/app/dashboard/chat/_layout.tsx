import RightSidebar from '@/components/Sidebar/RightSidebar'
import Sidebar from '@/components/Sidebar/Sidebar'

export default function layout({ children }: { children: React.ReactNode }) {
  return (
    <main className="relative flex w-full h-full">
      {/* <Sidebar /> */}
      {children}
      {/* <RightSidebar /> */}
    </main>
  )
}
