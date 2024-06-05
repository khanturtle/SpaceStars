import { SearchIcon } from '../Icons'
import './Input.css'

export interface IconInputProps
  extends React.InputHTMLAttributes<HTMLInputElement> {
  className?: string
  fill?: string
}

const IconInput = ({
  className,
  fill = '#84818A',
  ...props
}: IconInputProps) => {
  return (
    <div className={`search-input ${className}`}>
      <SearchIcon width="24" height="24" fill={fill} />
      <input type="text" placeholder="Search" {...props} />
    </div>
  )
}

export default IconInput
