export default function layout({ children }: { children: React.ReactNode }) {
  return (
    <main className="relative flex flex-row w-full h-full">{children}</main>
  )
}
