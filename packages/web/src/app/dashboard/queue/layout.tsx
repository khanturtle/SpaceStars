import NavHeader from '@/components/Navbar/NavHeader'

export default async function layout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    <div className="flex relative w-full h-[calc(100vh_-_100px)] overflow-hidden mx-auto my-0">
      <NavHeader
        title="QUEUE"
        description="게임을 같이 할 나의 친구를 찾아 드려요!"
      />
      {children}
    </div>
  )
}
