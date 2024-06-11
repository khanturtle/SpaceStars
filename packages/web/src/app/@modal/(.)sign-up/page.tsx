import Modal from '@/components/modal/modal'
import SignUpLayout from '@/containers/sign-up/SignUpLayout'

export default function Page() {
  return (
    <Modal>
      <SignUpLayout
        className={`h-full px-[240px] py-[50px] flex flex-col items-center justify-around`}
      />
    </Modal>
  )
}
