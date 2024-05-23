import './Input.css'

export interface InputProps
  extends React.InputHTMLAttributes<HTMLInputElement> {
  /** 추가적인 CSS 클래스를 지정 */
  className?: string
  /** input요소의 고유 id 지정, label 요소와 연결 */
  id: string
  /**  input 요소에 대한 라벨 텍스트를 지정 */
  label?: string
  /** input 요소가 필수 입력인지 여부를 지정 */
  required?: boolean
  /** input 요소가 수정가능한지 여부를 지정 */
  disabled?: boolean
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

      <div className="input--valid">v</div>
    </div>
  )
}

export default Input
