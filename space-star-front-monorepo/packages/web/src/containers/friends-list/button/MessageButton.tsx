import messageIcon from './message-svgrepo-com.png'

const MessageButton = () => {
  return (
    <button
      type="button"
      className="inline-flex items-center justify-center rounded-full bg-white p-2 uppercase leading-normal text-primary transition duration-150 ease-in-out hover:bg-gray-200 focus:bg-gray-200 focus:outline-none focus:ring-0 active:bg-gray-300 motion-reduce:transition-none"
    >
      <img src={messageIcon.src} alt="Button Icon" width="24" height="24" />
    </button>
  )
}
export default MessageButton
