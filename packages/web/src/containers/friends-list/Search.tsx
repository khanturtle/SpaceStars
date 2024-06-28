'use client'

import { useRouter } from 'next/navigation'

import { useState } from 'react'
import { SearchInput, ViewCardIcon, ViewListIcon } from '@packages/ui'



export const Search = () => {
    const [value, setValue] = useState<string>('')
  
    const handleSearch = () => {
      console.log(value)
    }
  
    return (
      <form className="" action={handleSearch}>
        <SearchInput value={value} onChange={(e) => setValue(e.target.value)} />
      </form>
    )
  }
  