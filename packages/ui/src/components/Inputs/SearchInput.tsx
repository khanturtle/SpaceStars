import { Ref } from 'react'
import { SearchIcon } from '../Icons'
import './Input.css'

export interface IconInputProps
  extends React.InputHTMLAttributes<HTMLInputElement> {
  className?: string
  fill?: string
  placeholder?: string
  inputRef?: Ref<HTMLInputElement>
}

const IconInput = ({
  className = '',
  fill = '#84818A',
  inputRef,
  ...props
}: IconInputProps) => {
  return (
    <div className={`search-input ${className}`}>
      <SearchIcon width="24" height="24" fill={fill} />
      <input ref={inputRef} type="text" {...props} />
    </div>
  )
}

export default IconInput
