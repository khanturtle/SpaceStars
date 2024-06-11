const FileIcon = ({
  width = '24',
  height = '25',
  viewBox = '0 0 24 25',
  fill = '#869AA9',
  ...props
}: {
  width?: string
  height?: string
  viewBox?: string
  fill?: string
}) => (
  <svg
    width={width}
    height={height}
    viewBox={viewBox}
    fill="none"
    xmlns="http://www.w3.org/2000/svg"
    {...props}
  >
    <path
      d="M10 9.2092V14.3254C10 14.8681 10.2107 15.3886 10.5858 15.7724C10.9609 16.1562 11.4696 16.3718 12 16.3718C12.5304 16.3718 13.0391 16.1562 13.4142 15.7724C13.7893 15.3886 14 14.8681 14 14.3254V7.16274C14 6.07723 13.5786 5.03618 12.8284 4.26861C12.0783 3.50104 11.0609 3.06982 10 3.06982C8.93913 3.06982 7.92172 3.50104 7.17157 4.26861C6.42143 5.03618 6 6.07723 6 7.16274V15.3486C6 16.9769 6.63214 18.5384 7.75736 19.6898C8.88258 20.8411 10.4087 21.488 12 21.488C13.5913 21.488 15.1174 20.8411 16.2426 19.6898C17.3679 18.5384 18 16.9769 18 15.3486V5.11628"
      stroke={fill}
      strokeWidth="1.4"
      strokeLinecap="round"
      strokeLinejoin="round"
    />
  </svg>
)

export default FileIcon
