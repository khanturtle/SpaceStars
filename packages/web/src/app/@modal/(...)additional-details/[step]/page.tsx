import Modal from '@/components/modal/modal'
import AdditionalDetailsLayout from '@/containers/additional-details/AdditionalDetailsLayout'

export default function Page({ params }: { params: { step: string } }) {
  return (
    <Modal className="h-[700px]">
      <AdditionalDetailsLayout
        className={`relative h-full pt-[90px] pb-[60px]
                  flex flex-col items-center`}
        step={params.step}
      />
    </Modal>
  )
}
