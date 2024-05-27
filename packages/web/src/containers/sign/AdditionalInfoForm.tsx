'use client'

import styles from './sign.module.css'
import { Button, Checkbox, Input } from '@packages/ui'
import { ChangeEvent, useState } from 'react'

export default function AdditionalInfoForm() {
  const [isChecked, setIsChecked] = useState(false)

  const handleCheckboxChange = (e: ChangeEvent<HTMLInputElement>) => {
    setIsChecked(e.target?.checked)
  }

  return (
    <>
      <div className={styles['form-wrapper']}>
        <Input
          id="email"
          label="이메일"
          disabled
          className={styles['input-box']}
        />

        <div className={`${styles['input-box']} ${styles.nickname}`}>
          <Input
            id="nickname"
            label="닉네임"
            required
            className={styles['input-box']}
          />
          <Button label="중복검사" size="small" primary className="h-[53px]" />
        </div>

        <Input id="gender" className={styles['input-box']} />
        <Input id="birthday" className={styles['input-box']} />

        <Checkbox
          className={styles['input-check']}
          id="agree"
          text="개인정보 수집동의"
          isChecked={isChecked}
          onChange={handleCheckboxChange}
        />
      </div>
    </>
  )
}
