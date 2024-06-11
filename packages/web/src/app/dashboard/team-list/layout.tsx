export default async function layout({
  children,
}: {
  children: React.ReactNode
}) {
  return <section className="flex-1">{children}</section>
}
