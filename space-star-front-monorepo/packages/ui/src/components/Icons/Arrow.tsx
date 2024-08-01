export interface Props {
  fill?: string
  width?: string
  height?: string
  viewBox?: string
  strokeWidth?: string
  type?: 'up' | 'down' | 'right' | 'left'
}

const Arrow = ({
  fill = 'var(--color-primary)',
  width = '12',
  height = '7',
  viewBox = '0 0 12 7',
  strokeWidth = '1.5',
  type = 'down',
  ...props
}: Props) => {
  const getRotation = (type: 'up' | 'down' | 'right' | 'left') => {
    switch (type) {
      case 'up':
        return 'rotate(180deg)'
      case 'right':
        return 'rotate(-90deg)'
      case 'left':
        return 'rotate(90deg)'
      case 'down':
      default:
        return 'rotate(0deg)'
    }
  }

  return (
    <svg
      width={width}
      height={height}
      viewBox={viewBox}
      fill="none"
      xmlns="http://www.w3.org/2000/svg"
      style={{ transform: getRotation(type) }}
      {...props}
    >
      <path
        d="M11 1L6 6L1 1"
        stroke={fill}
        strokeWidth={strokeWidth}
        strokeLinecap="round"
        strokeLinejoin="round"
      />
    </svg>
  )
}

export default Arrow
