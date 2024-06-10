import Sidebar from '@/components/Sidebar/Sidebar'

export default async function layout({
  children,
  rightSidebar,
}: {
  children: React.ReactNode
  rightSidebar: React.ReactNode
}) {
  return (
    <main className="relative flex w-full h-full">
      <Sidebar />
      {children}
      {rightSidebar}
    </main>
  )
}
