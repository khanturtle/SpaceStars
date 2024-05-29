import AdditionalInfoLayout from '@/containers/additional-info/AdditionalInfoLayout'

export default function Page() {
  return (
    <section className="w-[100vw] h-[100vh] fixed inset-0 flex items-center justify-center bg-[url('/images/BG.svg')] bg-cover bg-center bg-no-repeat overflow-hidden z-10">
      <AdditionalInfoLayout
        className={`w-[90%] h-[90%] sm:w-[80%] sm:h-[80%] 
                  md:w-[60%] md:h-[60%] lg:w-[50%] lg:h-[60%]
                  flex flex-col items-center`}
      />
    </section>
  )
}
