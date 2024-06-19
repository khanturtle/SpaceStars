'use client'

import React, { ChangeEvent, useState } from 'react'

import { SearchInput } from '@packages/ui'

export default function SearchUserBox() {
  const [value, setValue] = useState<string>('')

  const handleSearch = (e: ChangeEvent<HTMLInputElement>) => {
    setValue(e.target.value)
    console.log(value)
  }

  return (
    <div className="h-14 mx-0 my-[26px]">
      <SearchInput
        className="h-14"
        placeholder="Search people or message"
        value={value}
        onChange={handleSearch}
      />
    </div>
  )
}
