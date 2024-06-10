import './Avatar.css'

const Mask = ({
  image,
  fill,
  className,
}: {
  image?: string
  fill?: string
  className?: string
}) => (
  <svg
    width="48"
    height="48"
    viewBox="0 0 48 48"
    fill="none"
    xmlns="http://www.w3.org/2000/svg"
    className={className}
  >
    <mask
      id="mask0_905_1868"
      maskUnits="userSpaceOnUse"
      x="0"
      y="0"
      width="48"
      height="48"
    >
      <path
        d="M32 1H16C7.71573 1 1 7.71573 1 16V32C1 40.2843 7.71572 47 16 47H26.1446C30.0106 47 33.1446 43.8297 33.1446 39.9637C33.1446 36.1376 36.2462 33 40.0723 33C43.8984 33 47 29.8984 47 26.0723V16C47 7.71573 40.2843 1 32 1Z"
        fill="#fff"
        stroke="black"
      />
    </mask>
    <g mask="url(#mask0_905_1868)">
      {image ? (
        <image
          width="50"
          height="50"
          transform="translate(-1 -1)"
          href={image}
        />
      ) : (
        <rect width="50" height="50" transform="translate(-1 -1)" fill={fill} />
      )}
    </g>
  </svg>
)

export interface AvatarProps {
  className?: string
  image_url?: string
}

const Avatar = ({ className, image_url }: AvatarProps) => {
  return (
    <div className={`avatar-container ${className}`}>
      <div className="avatar-wrapper">
        <Mask image={image_url} fill="#E9B6B6" className="z-10" />
        <span className="avatar-button">v</span>
      </div>
    </div>
  )
}

export default Avatar
