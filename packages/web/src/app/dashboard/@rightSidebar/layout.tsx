export default function layout({ children }: { children: React.ReactNode }) {
  return (
    <div className="min-w-[328px] h-[calc(100vh_-_100px)] sticky right-0 top-[100px]">
      {children}
    </div>
  )
}
