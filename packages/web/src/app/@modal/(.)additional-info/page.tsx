import Modal from '@/components/modal/modal'
import AdditionalInfoLayout from '@/containers/additional-info/AdditionalInfoLayout'

export default function Page() {
  return (
    <Modal className="h-[700px]">
      <AdditionalInfoLayout
        className={`relative h-full pt-[90px] pb-[85px]
                  flex flex-col items-center`}
      />
    </Modal>
  )
}
