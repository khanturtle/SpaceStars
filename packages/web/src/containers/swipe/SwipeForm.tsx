'use client'

import { useContext, useEffect, useState } from 'react'
import { useFormState } from 'react-dom'

import { Button, Textarea } from '@packages/ui'

import { postSwipe } from '@/apis/actionSwipe'

import Toast from '@/components/Toast/toast'
import { ModalContext } from '@/components/providers/modal-provider'

import styles from './swipe.module.css'

export default function SwipeForm({ uuid }: { uuid: string }) {
  const { closeModal } = useContext(ModalContext)
  const initialState = {
    code: 0,
    message: '',
    result: null,
  }

  const [state, formAction] = useFormState(postSwipe, initialState)

  const maxLength = 30
  const [text, setText] = useState('')

  const [toastMessage, setToastMessage] = useState('')
  const [toastType, setToastType] = useState<'info' | 'error'>('info')

  const handleTextChange = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    const { value } = e.target
    if (value.length <= maxLength) {
      setText(value)
    }
  }

  const showToast = (message: string, type: 'info' | 'error') => {
    setToastMessage(message)
    setToastType(type)
  }

  /** 스와이프 요청 */
  useEffect(() => {
    if (state.code === 201) {
      showToast(state.message, 'info')

      // 모달이 닫히면 안보임
      setTimeout(() => {
        closeModal()
      }, 1000)
    } else {
      showToast(state.message, 'error')
    }
  }, [state])

  return (
    <>
      {toastMessage && (
        <Toast message={toastMessage} type={toastType} position="top" />
      )}
      <form
        action={formAction}
        className="flex flex-col items-center justify-center"
      >
        <input type="hidden" name="matchToMember" value={uuid} />

        <div className="relative h-[100px] w-[300px] mb-[16px]">
          <Textarea
            id="memo"
            name="memo"
            maxLength={maxLength}
            value={text}
            onChange={handleTextChange}
            className={styles.textarea}
          />
          <div className={styles['character-count']}>
            {text.length}/{maxLength}
          </div>
        </div>

        <Button
          type="submit"
          label="요청하기"
          primary
          shape="oval"
          size="full"
          className={styles['memo-button']}
        />
      </form>
    </>
  )
}
