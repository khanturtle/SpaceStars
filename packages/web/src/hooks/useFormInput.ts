import { useState } from 'react'

export function useFormInput(initialValue: unknown) {
  const [value, setValue] = useState(initialValue)

  // FIXME: type
  function handleChange(e) {
    setValue(e.target.value)
  }

  const inputProps = {
    value,
    onChange: handleChange,
  }

  return inputProps
}
