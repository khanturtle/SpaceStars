export interface Props {
  width?: string
  height?: string
  viewBox?: string
  fill?: string
}

const Star = ({
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
        fillRule="evenodd"
        clipRule="evenodd"
        d="M12 17.27L18.18 21L16.54 13.97L22 9.24L14.81 8.63L12 2L9.19 8.63L2 9.24L7.46 13.97L5.82 21L12 17.27Z"
        fill={fill}
      />
    </svg>
  )
}

export default Star
