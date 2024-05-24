import AdditionalInfoLayout from '../../containers/sign/AdditionalInfoLayout'

export default function Page() {
  return (
    <section className="w-[100vw] h-[100vh] fixed inset-0 flex items-center justify-center bg-[url('/images/BG.svg')] bg-cover bg-center bg-no-repeat overflow-hidden z-10">
      <AdditionalInfoLayout />
    </section>
  )
}
