import './Input.css'

export interface CheckboxProps {
  className?: string
  /** 고유 id, label 요소와 연결 */
  id: string
  /** 라벨 텍스트 */
  text?: string
  /** 체크 여부 */
  isChecked?: boolean
  /** 수정 가능 여부 */
  disabled?: boolean
  onChange?: (event: React.ChangeEvent<HTMLInputElement>) => void
}

const Checkbox = ({
  className,
  id,
  text,
  isChecked = false,
  disabled = false,
  onChange,
  ...props
}: CheckboxProps) => {
  return (
    <div className={`checkbox-wrapper ${className}`}>
      <label htmlFor={id} className="checkbox-label">
        <input
          id={id}
          type="checkbox"
          className="invisible"
          disabled={disabled}
          checked={isChecked}
          onChange={onChange}
          {...props}
        />
        <div className="checkbox">
          <svg width="20px" height="20px" viewBox="0 0 20 20">
            <path d="M3,1 L17,1 L17,1 C18.1045695,1 19,1.8954305 19,3 L19,17 L19,17 C19,18.1045695 18.1045695,19 17,19 L3,19 L3,19 C1.8954305,19 1,18.1045695 1,17 L1,3 L1,3 C1,1.8954305 1.8954305,1 3,1 Z"></path>
            <polyline points="4 11 8 15 16 6"></polyline>
          </svg>
        </div>
        {text && <span>{text}</span>}
      </label>
    </div>
  )
}

export default Checkbox
