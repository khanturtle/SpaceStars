'use client'

import { useRouter } from 'next/navigation'

import { useState } from 'react'

import { SearchInput } from '@packages/ui'

import styles from './teamList.module.css'

export const Search = () => {
  const [value, setValue] = useState<string>('')

  const handleSearch = (e) => {
    e.preventDefault()
    console.log(value)
  }

  return (
    <form className="" action={handleSearch}>
      <SearchInput value={value} onChange={(e) => setValue(e.target.value)} />
    </form>
  )
}

export const SubButton = ({
  name,
  icon,
  queryKey,
  queryValue,
  searchParams,
}: {
  name: string
  icon?: React.ReactNode
  queryKey?: string
  queryValue?: string
  searchParams?: { [key: string]: string }
}) => {
  const router = useRouter()

  const handleQueryUpdate = () => {
    if (!queryKey || !queryValue) return

    const currentParams = new URLSearchParams(searchParams)
    currentParams.set(queryKey, queryValue)
    router.push(`?${currentParams.toString()}`)
  }

  return (
    <button type="button" onClick={handleQueryUpdate} className={styles.button}>
      {icon && icon}
      {name}
    </button>
  )
}

export const ListToggle = () => {
  return <div>ㅇㅅㅇ</div>
}
