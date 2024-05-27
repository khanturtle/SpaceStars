import './Icons.css'

export interface ThemeProps {
  className?: string
  width?: string
  height?: string
  viewBox?: string
  main_fill?: string
  sub_fill?: string
}

const Theme = ({
  className,
  width = '36',
  height = '35',
  viewBox = '0 0 36 35',
  main_fill = '#0C0C0C',
  sub_fill = 'white',
  ...props
}: ThemeProps) => {
  return (
    <svg
      className={`theme-icon ${className}`}
      width={width}
      height={height}
      viewBox={viewBox}
      fill="none"
      xmlns="http://www.w3.org/2000/svg"
      {...props}
    >
      <mask id="path-1-inside-1_894_1855" fill="white">
        <path d="M16.3636 0.000732422C12.0237 0.000732367 7.86158 1.84444 4.7928 5.12625C1.72402 8.40806 3.79407e-07 12.8592 0 17.5003C-3.79407e-07 22.1415 1.72402 26.5926 4.7928 29.8744C7.86158 33.1563 12.0237 35 16.3636 35L16.3636 17.5003L16.3636 0.000732422Z" />
      </mask>
      <path
        d="M16.3636 0.000732422C12.0237 0.000732367 7.86158 1.84444 4.7928 5.12625C1.72402 8.40806 3.79407e-07 12.8592 0 17.5003C-3.79407e-07 22.1415 1.72402 26.5926 4.7928 29.8744C7.86158 33.1563 12.0237 35 16.3636 35L16.3636 17.5003L16.3636 0.000732422Z"
        stroke={main_fill}
        fill={sub_fill}
        stroke-width="4"
        mask="url(#path-1-inside-1_894_1855)"
      />
      <path
        d="M19.3614 34.8167C23.7742 34.7854 27.9957 32.9275 31.0971 29.6518C34.1985 26.376 35.9259 21.9507 35.8991 17.3494C35.8724 12.7481 34.0937 8.34763 30.9545 5.11611C27.8152 1.8846 23.5725 0.0867203 19.1596 0.117996L19.2605 17.4673L19.3614 34.8167Z"
        fill={main_fill}
      />
      <ellipse
        cx="23.2363"
        cy="28.1933"
        rx="2.29091"
        ry="2.26847"
        fill={sub_fill}
      />
      <ellipse
        cx="10.8"
        cy="8.74918"
        rx="2.29091"
        ry="2.26847"
        fill={main_fill}
      />
    </svg>
  )
}

export default Theme
