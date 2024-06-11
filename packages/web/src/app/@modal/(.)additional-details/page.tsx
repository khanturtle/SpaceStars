import Modal from '@/components/modal/modal'
import AdditionalDetailsLayout from '@/containers/additional-details/AdditionalDetailsLayout'

export default function page({
  searchParams,
}: {
  searchParams: { [key: string]: string }
}) {
  return (
    <Modal>
      <AdditionalDetailsLayout
        className="relative h-full px-[204px] pt-[90px] pb-[60px] flex flex-col items-center"
        step={searchParams.step}
      />
    </Modal>
  )
}
