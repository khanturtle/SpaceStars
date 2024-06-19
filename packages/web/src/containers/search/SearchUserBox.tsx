'use client'

import React, { ChangeEvent, useState } from 'react'

import { SearchInput } from '@packages/ui'
import { getUserByNickname } from '@/apis/member'

export default function SearchUserBox() {
  const [value, setValue] = useState<string>('')

  const handleSearch = async (e: ChangeEvent<HTMLInputElement>) => {
    setValue(e.target.value)

    // const res = await getUserByNickname(e.target.value)
  }

  return (
    <div className="h-14 mx-0 my-[26px]">
      <SearchInput
        className="h-14"
        placeholder="Search people or message"
        value={value}
        onChange={handleSearch}
      />

      <div>결과</div>
    </div>
  )
}
