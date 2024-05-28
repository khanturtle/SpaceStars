export interface Props {
  fill?: string
  strokeWidth?: string
  width?: string
  height?: string
  viewBox?: string
}

const Check = ({
  fill = 'var(--color-primary)',
  strokeWidth = '1.5',
  width = '14',
  height = '11',
  viewBox = '0 0 14 11',
  ...props
}: Props) => (
  <svg
    width={width}
    height={height}
    viewBox={viewBox}
    fill="none"
    xmlns="http://www.w3.org/2000/svg"
    {...props}
  >
    <g clipPath="url(#clip0_604_23285)">
      <path
        d="M12.3337 1.5L5.00033 8.83333L1.66699 5.5"
        stroke={fill}
        strokeWidth={strokeWidth}
        strokeLinecap="round"
        strokeLinejoin="round"
      />
    </g>
    <defs>
      <clipPath id="clip0_604_23285">
        <rect
          width="14"
          height="10"
          fill="white"
          transform="translate(0 0.5)"
        />
      </clipPath>
    </defs>
  </svg>
)

export default Check
