'use client'

import { useRouter } from 'next/navigation'
import Image from 'next/image'

import { useSession } from 'next-auth/react'

import { ChangeEvent, useCallback, useEffect, useRef, useState } from 'react'

import { SearchInput } from '@packages/ui'

import { ProfileImageType } from '@/types/type'

import styles from './searchbox.module.css'
import { defaultImage } from '@/store/defaultState'

interface SearchByNicknameType {
  index: number
  nickname: string
  uuid: string
}

/** 닉네임으로 회원 조회 */
async function searchByNickname(
  value: string,
): Promise<SearchByNicknameType[]> {
  try {
    const response = await fetch(
      `${process.env.NEXT_PUBLIC_API_URL_V1}/auth/search?nickname=${value}`,
      {
        headers: {
          'Content-Type': 'application/json',
        },
      },
    )

    if (!response.ok) {
      throw new Error('Failed to searchByNickname')
    }
    const data = await response.json()
    return data.result
  } catch (error) {
    console.error(error)
    return []
  }
}

/** UUID로 대표 프로필 조회 */
async function getMainProfileImageByUuid(
  uuid: string,
  token?: string,
): Promise<ProfileImageType | undefined> {
  try {
    const response = await fetch(
      `${process.env.NEXT_PUBLIC_API_URL_V1}/profile/image/main/${uuid}`,
      {
        headers: {
          'Content-Type': 'application/json',
          Authorization: token ? token : '',
        },
      },
    )
    if (!response.ok) {
      throw new Error('Failed to getMainProfileImageByUuid')
    }

    const data = await response.json()
    return data.result
  } catch (error) {
    console.error(error)
    return undefined
  }
}

const SearchResultItem = ({ result }: { result: any }) => {
  const { data: session, status } = useSession()
  const [profileImage, setProfileImage] = useState(defaultImage)

  const router = useRouter()

  const handleClick = () => {
    // 회원이면, 프로필 모달
    if (status === 'authenticated') {
      router.push(`/profile/${result.uuid}`)
    } else {
      // 비회원이면, 회원가입
      router.push('/sign-up')
    }
  }

  // 대표 프로필 받아오기
  useEffect(() => {
    const fetchData = async (token: string) => {
      const res = await getMainProfileImageByUuid(result.uuid, token)
      if (res) {
        setProfileImage(res.profileImageUrl ?? defaultImage)
      }
    }

    if (session) {
      const token = session.user?.data.accessToken
      fetchData(token)
    }
  }, [session])

  return (
    <button
      onClick={handleClick}
      className={`w-full hover:bg-gray-200 ${styles['search-result-item']}`}
    >
      <Image
        src={profileImage}
        alt={result.nickname}
        width={20}
        height={20}
        className={styles.image}
      />
      <p className={styles.nickname}>{result.nickname}</p>
    </button>
  )
}

export default function SearchBox() {
  const [value, setValue] = useState<string>('')
  const [searchResults, setSearchResults] = useState<SearchByNicknameType[]>([])
  const [isPopupVisible, setIsPopupVisible] = useState(false)

  const debounceTimeoutRef = useRef<ReturnType<typeof setTimeout> | null>(null)
  const throttleTimeoutRef = useRef<ReturnType<typeof setTimeout> | null>(null)

  const searchInputRef = useRef<HTMLInputElement>(null)

  const handleSearch = useCallback(async (e: ChangeEvent<HTMLInputElement>) => {
    const searchValue = e.target.value.trim() // 양쪽 공백 제거
    setValue(searchValue)

    // 검색 값이 비어있으면 검색하지 않음
    if (!searchValue) {
      setSearchResults([])
      setIsPopupVisible(false)
      return
    }

    // 디바운싱 처리
    if (debounceTimeoutRef.current) {
      clearTimeout(debounceTimeoutRef.current)
    }
    debounceTimeoutRef.current = setTimeout(async () => {
      const results = await searchByNickname(searchValue)

      // 검색 결과가 있으면 팝업창을 보여줌
      setSearchResults(results)
      setIsPopupVisible(results.length > 0)
    }, 500)

    // 쓰로틀링 처리
    if (throttleTimeoutRef.current) {
      return
    }
    throttleTimeoutRef.current = setTimeout(() => {
      throttleTimeoutRef.current = null
    }, 1000)
  }, [])

  const handlePopupClose = () => {
    setTimeout(() => {
      setIsPopupVisible(false)
    }, 300)
  }

  const handleKeyDown = (event: KeyboardEvent) => {
    if ((event.metaKey || event.ctrlKey) && event.key === 'K') {
      event.preventDefault() // 기본 동작 막기
      searchInputRef.current?.focus() // 검색창에 포커스 맞추기
    }
  }

  useEffect(() => {
    document.addEventListener('keydown', handleKeyDown)

    return () => {
      document.removeEventListener('keydown', handleKeyDown)
    }
  }, [])

  return (
    <div className="relative">
      <SearchInput
        className="h-14"
        placeholder="Search.. (Press Ctrl+K)"
        value={value}
        onChange={handleSearch}
        onFocus={handleSearch}
        onBlur={handlePopupClose}
        inputRef={searchInputRef}
      />

      {isPopupVisible && (
        <div className="absolute top-full left-0 right-0 bg-white shadow-lg rounded-md p-4 z-[9999] max-h-[300px] overflow-y-auto">
          {searchResults.length > 0 ? (
            searchResults.map((result) => (
              <SearchResultItem key={result.index} result={result} />
            ))
          ) : (
            <div>검색 결과가 없습니다.</div>
          )}
        </div>
      )}
    </div>
  )
}
