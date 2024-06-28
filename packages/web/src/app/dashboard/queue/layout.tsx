import BackGroundTextBox from '@/components/Background/BackGroundTextBox'

export default async function layout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    <div className="flex relative w-full h-[calc(100vh_-_100px)] overflow-hidden mx-auto my-0">
      <BackGroundTextBox text="GAMER SEARCHING" />

      {children}
    </div>
  )
}
