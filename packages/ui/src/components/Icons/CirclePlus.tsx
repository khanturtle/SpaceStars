export interface Props {
  stroke?: string
  fill?: string
  width?: string
  height?: string
  viewBox?: string
}

const CirclePlus = ({
  stroke = '#D1D1D1',
  fill = '#ACADB1',
  width = '42',
  height = '42',
  viewBox = '0 0 42 42',
  ...props
}: Props) => (
  <svg
    xmlns="http://www.w3.org/2000/svg"
    width={width}
    height={height}
    viewBox={viewBox}
    fill="none"
    {...props}
  >
    <rect
      x="1"
      y="1"
      width="40"
      height="40"
      rx="20"
      stroke={stroke}
      strokeWidth="1.5"
      strokeLinecap="round"
      strokeDasharray="4 4"
    />
    <path
      d="M21 27C20.8674 27 20.7402 26.9473 20.6464 26.8536C20.5527 26.7598 20.5 26.6326 20.5 26.5V17.5C20.5 17.3674 20.5527 17.2403 20.6464 17.1465C20.7402 17.0527 20.8674 17 21 17C21.1326 17 21.2598 17.0527 21.3536 17.1465C21.4473 17.2403 21.5 17.3674 21.5 17.5V26.5C21.5 26.6326 21.4473 26.7598 21.3536 26.8536C21.2598 26.9473 21.1326 27 21 27Z"
      fill={fill}
      stroke={stroke}
      strokeLinecap="round"
    />
    <path
      d="M25.5 22.5H16.5C16.3674 22.5 16.2402 22.4473 16.1464 22.3536C16.0527 22.2598 16 22.1326 16 22C16 21.8674 16.0527 21.7403 16.1464 21.6465C16.2402 21.5527 16.3674 21.5 16.5 21.5H25.5C25.6326 21.5 25.7598 21.5527 25.8536 21.6465C25.9473 21.7403 26 21.8674 26 22C26 22.1326 25.9473 22.2598 25.8536 22.3536C25.7598 22.4473 25.6326 22.5 25.5 22.5Z"
      fill={fill}
      stroke={stroke}
      strokeLinecap="round"
    />
  </svg>
)

export default CirclePlus
