'use client'

import { BellIcon } from 'lucide-react'

export const Alarm = () => {
  const handleClick = () => {
    console.log('alarm clicked')
  }
  return (
    <button type="button" onClick={handleClick} className="mr-8">
      <BellIcon size={28} stroke="var(--color-icon)" strokeWidth={2} />
    </button>
  )
}
