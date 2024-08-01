import menuIcon from './three-dots-vertical-svgrepo-com.png'

const MenuButton = () => {
  return (
    <button
      type="button"
      className="ml-2 inline-flex items-center justify-center rounded-full bg-white p-2 uppercase leading-normal text-primary transition duration-150 ease-in-out hover:bg-gray-200 focus:bg-gray-200 focus:outline-none focus:ring-0 active:bg-gray-300 motion-reduce:transition-none"
    >
      <img src={menuIcon.src} alt="Menu" height="24px" width="24px" />
    </button>
  )
}
export default MenuButton
