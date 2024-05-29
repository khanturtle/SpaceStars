import Modal from '@/components/modal/modal'
import SignInLayout from '@/containers/sign-in/SignInLayout'

export default function Page() {
  return (
    <Modal className="h-[480px]">
      <SignInLayout className="h-full py-[50px] flex flex-col items-center justify-around" />
    </Modal>
  )
}
