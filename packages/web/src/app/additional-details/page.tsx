import AdditionalDetailsLayout from '@/containers/additional-details/AdditionalDetailsLayout'

export default function page({
  searchParams,
}: {
  searchParams: { [key: string]: string }
}) {
  
  return (
    <section className="w-[100vw] h-[100vh] fixed inset-0 flex items-center justify-center bg-[url('/images/BG.svg')] bg-cover bg-center bg-no-repeat overflow-hidden z-10">
      <AdditionalDetailsLayout step={searchParams.step} />
    </section>
  )
}
