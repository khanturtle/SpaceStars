import RightSidebar from '@/components/Sidebar/RightSidebar'
import Sidebar from '@/components/Sidebar/Sidebar'

export default function layout({ children }: { children: React.ReactNode }) {
  return (
    <main className="flex w-full h-full">
      <Sidebar />
      <section className="flex-1 px-[50px] py-[42px] overflow-auto">
        {children}
      </section>
      <RightSidebar />
    </main>
  )
}
