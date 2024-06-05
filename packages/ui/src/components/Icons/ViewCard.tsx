export interface Props {
  width?: string
  height?: string
  viewBox?: string
  fill?: string
}

const ViewCard = ({
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
        d="M4 11H9V5H4V11V11ZM4 18H9V12H4V18V18ZM10 18H15V12H10V18V18ZM16 18H21V12H16V18V18ZM10 11H15V5H10V11V11ZM16 5V11H21V5H16V5Z"
        fill={fill}
      />
    </svg>
  )
}

export default ViewCard
