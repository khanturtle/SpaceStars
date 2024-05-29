import Arrow from '../Icons/Arrow'
import './select.css'

type Option = {
  value: string
  label: string
}

export interface SelectProps {
  className?: string
  id: string
  label?: string
  required?: boolean
  options: Option[]
  selectedOption?: string
  onChange?: (value: string) => void
}

const Select = ({
  className,
  id = 'id',
  options,
  label,
  required,
  selectedOption,
  onChange,
  ...props
}: SelectProps) => {
  const isRequired = required ? 'required--label' : ''

  const handleOptionChange = (selectedValue: string) => {
    if (onChange) {
      onChange(selectedValue)
    }
  }

  return (
    <div className={`select-box ${className}`}>
      <div
        className="select-box__current"
        tabIndex={1}
        onClick={() => handleOptionChange(selectedOption!)}
      >
        {label && (
          <label htmlFor={id} className={isRequired}>
            {label}
          </label>
        )}
        {options.map((option) => (
          <div className="select-box__value" key={option.value}>
            <input
              className="select-box__input"
              type="radio"
              id={option.value}
              value={option.value}
              name="select-box"
              checked={selectedOption === option.value}
              onChange={() => handleOptionChange(option.value)}
              {...props}
            />
            <p className="select-box__input-text">{option.label}</p>
          </div>
        ))}

        <div className="select-box__icon">
          <Arrow type="down" width="14" height="14" />
        </div>
      </div>

      <ul className="select-box__list">
        {options.map((option) => (
          <li
            key={option.value}
            onClick={() => handleOptionChange(option.value)}
          >
            <label
              className="select-box__option"
              htmlFor={option.value}
              aria-hidden={true}
            >
              {option.label}
            </label>
          </li>
        ))}
      </ul>
    </div>
  )
}

export default Select
