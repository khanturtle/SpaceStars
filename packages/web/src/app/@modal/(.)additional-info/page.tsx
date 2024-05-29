import Modal from '@/components/modal/modal'
import AdditionalInfoLayout from '@/containers/additional-info/AdditionalInfoLayout'

export default function Page() {
  return (
    <Modal>
      <AdditionalInfoLayout
        className={`h-full py-[50px]
                  flex flex-col items-center justify-around`}
      />
    </Modal>
  )
}
