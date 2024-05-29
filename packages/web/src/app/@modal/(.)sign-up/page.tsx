import Modal from '@/components/modal/modal'
import SignUpLayout from '@/containers/sign-up/SignUpLayout'

export default function Page() {
  return (
    <Modal className="h-[480px]">
      <SignUpLayout
        className={`h-full py-[50px]
                  flex flex-col items-center justify-around`}
      />
    </Modal>
  )
}
