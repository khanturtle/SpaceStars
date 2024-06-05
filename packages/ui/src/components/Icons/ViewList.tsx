export interface Props {
  width?: string
  height?: string
  viewBox?: string
  fill?: string
}

const ViewList = ({
  width = '24',
  height = '24',
  viewBox = '0 0 24 24',
  fill = '#000',
  ...props
}: Props) => {
  return (
    <svg
      width={width}
      height={height}
      viewBox={viewBox}
      fill="none"
      xmlns="http://www.w3.org/2000/svg"
      {...props}
    >
      <path
        fill-rule="evenodd"
        clip-rule="evenodd"
        d="M21 9H8V6H21V9V9ZM21 14H8V11H21V14V14ZM21 19H8V16H21V19V19ZM6 19H3V16H6V19V19ZM6 6V9H3V6H6V6ZM6 14H3V11H6V14V14Z"
        fill={fill}
      />
    </svg>
  )
}

export default ViewList
