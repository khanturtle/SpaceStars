import Modal from '@/components/modal/modal'
import SignInLayout from '@/containers/sign-in/SignInLayout'

export default function Page() {
  return (
    <Modal>
      <SignInLayout className="h-full px-[240px] py-[50px] flex flex-col items-center justify-around" />
    </Modal>
  )
}
