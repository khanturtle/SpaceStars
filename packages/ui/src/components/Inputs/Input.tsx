import { CheckIcon } from '../Icons'
import './Input.css'

export interface InputProps
  extends React.InputHTMLAttributes<HTMLInputElement> {
  className?: string
  /** 고유 id, label 요소와 연결 */
  id: string
  /**  라벨 텍스트 */
  label?: string
  /** 필수 입력 여부 */
  required?: boolean
  /** 수정 가능 여부 */
  disabled?: boolean
  value?: string
  onChange?: (event: React.ChangeEvent<HTMLInputElement>) => void
  onFocus?: (event: React.FocusEvent<HTMLInputElement>) => void
  onBlur?: (event: React.FocusEvent<HTMLInputElement>) => void
}

const Input = ({
  className,
  id = 'id',
  label,
  required,
  disabled = false,
  ...props
}: InputProps) => {
  const isDisabled = disabled ? 'input--disabled' : ''
  const isRequired = required ? 'required--label' : ''

  return (
    <div className={`ui-wrapper ${isDisabled} ${className}`}>
      <div className="input-wrapper">
        {label && (
          <legend>
            <label htmlFor={id} className={isRequired}>
              {label}
            </label>
          </legend>
        )}

        <div className="textfield">
          <input
            id={id}
            type="text"
            autoComplete="off"
            disabled={disabled}
            {...props}
          />
        </div>
      </div>

      <div className="input--valid">
        <CheckIcon fill={disabled ? 'white' : 'var(--color-primary)'} />
      </div>
    </div>
  )
}

export default Input
