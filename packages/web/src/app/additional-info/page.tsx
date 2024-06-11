import FakeModal from '@/components/modal/fakeModal'
import AdditionalInfoLayout from '@/containers/additional-info/AdditionalInfoLayout'

export default function Page() {
  return (
    <section className="w-[100vw]  inset-0 flex items-center justify-center overflow-hidden">
      <FakeModal className="h-[700px]" onCloseRoute="/">
        <AdditionalInfoLayout
          className={`relative h-full px-[204px] pt-[90px] pb-[85px]
                  flex flex-col items-center`}
        />
      </FakeModal>
    </section>
  )
}
