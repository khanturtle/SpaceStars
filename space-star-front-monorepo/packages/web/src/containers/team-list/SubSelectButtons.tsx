'use client'

import { useRouter } from 'next/navigation'

import { useState } from 'react'

import { SearchInput, ViewCardIcon, ViewListIcon } from '@packages/ui'

import styles from './teamList.module.css'

export const Search = () => {
  const [value, setValue] = useState<string>('')

  const handleSearch = () => {
    // console.log(value)
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
  onClick,
}: {
  name: string
  icon?: React.ReactNode
  queryKey?: string
  queryValue?: string
  searchParams?: { [key: string]: string }
  onClick?: () => void
}) => {
  const router = useRouter()

  const isActive =
    queryKey &&
    queryValue &&
    searchParams &&
    searchParams[queryKey] === queryValue

  const handleQueryUpdate = () => {
    if (!queryKey || !queryValue) return

    const currentParams = new URLSearchParams(searchParams)

    if (isActive) {
      // 쿼리 파라미터 삭제
      currentParams.delete(queryKey)
    } else {
      // 새로운 쿼리 파라미터 추가
      currentParams.set(queryKey, queryValue)
    }

    router.push(`?${currentParams.toString()}`)
  }

  return (
    <button
      type="button"
      onClick={onClick ?? handleQueryUpdate}
      className={`${styles['icon-button']} ${isActive && styles.active}`}
    >
      {icon && icon}
      {name && <span className={styles['icon-button-name']}>{name}</span>}
    </button>
  )
}

export const ViewToggle = ({
  searchParams,
}: {
  searchParams: { [key: string]: string }
}) => {
  const view = searchParams.view === 'card' || searchParams.view === undefined
  const router = useRouter()

  const handleToggle = (newView: 'card' | 'list') => {
    const currentParams = new URLSearchParams(searchParams)
    currentParams.set('view', newView)
    router.push(`?${currentParams.toString()}`)
  }

  return (
    <div className={styles['icon-buttons']}>
      <button
        className={styles['icon-button']}
        type="button"
        aria-label="Card View"
        onClick={() => handleToggle('card')}
      >
        <ViewCardIcon fill={view ? '#009580' : '#84818A'} />
      </button>
      <button
        className={styles['icon-button']}
        type="button"
        aria-label="List View"
        onClick={() => handleToggle('list')}
      >
        <ViewListIcon fill={view ? '#84818A' : '#009580'} />
      </button>
    </div>
  )
}
