const GameIcon = ({
  width = '16',
  height = '11',
  viewBox = '0 0 16 11',
  fill = '#7D12FF',
  ...props
}: {
  width?: string
  height?: string
  viewBox?: string
  fill?: string
}) => (
  <svg
    xmlns="http://www.w3.org/2000/svg"
    width={width}
    height={height}
    viewBox={viewBox}
    fill="none"
    {...props}
  >
    <path
      d="M10.9259 0.5H5C2.24444 0.5 0 2.74444 0 5.5C0 8.25556 2.24444 10.5 5 10.5H10.9259C13.6815 10.5 15.9259 8.25556 15.9259 5.5C15.9259 2.74444 13.6815 0.5 10.9259 0.5ZM10.9259 9.38889H5C2.85185 9.38889 1.11111 7.64074 1.11111 5.5C1.11111 3.35926 2.85185 1.61111 5 1.61111H10.9259C13.0741 1.61111 14.8148 3.35926 14.8148 5.5C14.8148 7.64074 13.0741 9.38889 10.9259 9.38889ZM13.1481 4.38889C13.1481 4.7963 12.8148 5.12963 12.4074 5.12963C12 5.12963 11.6667 4.7963 11.6667 4.38889C11.6667 3.98148 12 3.64815 12.4074 3.64815C12.8148 3.64815 13.1481 3.98148 13.1481 4.38889ZM10.9259 6.61111C10.9259 7.01852 10.5926 7.35185 10.1852 7.35185C9.77778 7.35185 9.44444 7.01852 9.44444 6.61111C9.44444 6.2037 9.77778 5.87037 10.1852 5.87037C10.5926 5.87037 10.9259 6.2037 10.9259 6.61111ZM7.03704 5.5C7.03704 5.8037 6.78519 6.05556 6.48148 6.05556H5.55556V6.98148C5.55556 7.28518 5.3037 7.53704 5 7.53704C4.6963 7.53704 4.44444 7.28518 4.44444 6.98148V6.05556H3.51852C3.21481 6.05556 2.96296 5.8037 2.96296 5.5C2.96296 5.1963 3.21481 4.94444 3.51852 4.94444H4.44444V4.01852C4.44444 3.71481 4.6963 3.46296 5 3.46296C5.3037 3.46296 5.55556 3.71481 5.55556 4.01852V4.94444H6.48148C6.78519 4.94444 7.03704 5.1963 7.03704 5.5Z"
      fill={fill}
    />
  </svg>
)

export default GameIcon