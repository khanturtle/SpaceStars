'use client'

import React, { ChangeEvent, useState } from 'react'

import { SearchInput } from '@packages/ui'

export default function SearchBox() {
  const [value, setValue] = useState<string>('')

  // TODO: 리바운싱?
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
