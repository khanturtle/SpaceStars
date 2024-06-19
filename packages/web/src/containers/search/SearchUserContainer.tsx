'use client'

import Image from 'next/image'

import React, { ChangeEvent, useState } from 'react'

import { SearchInput } from '@packages/ui'

import { getUserByNickname } from '@/apis/member'
import { getMainProfileImageByUuid } from '@/apis/getProfileImage'
import Link from 'next/link'

// TODO: 타입 수정
export default function SearchUserContainer({
  accessToken,
}: {
  accessToken: any
}) {
  const [value, setValue] = useState<string>('')
  const [targetUuid, setTargetUuid] = useState<string>('')
  const [targetNickname, setTargetNickname] = useState<string>('')
  const [targetProfile, setTargetProfile] = useState<string>('')

  const handleChange = async (e: ChangeEvent<HTMLInputElement>) => {
    setValue(e.target.value)
    setTargetNickname('')
    setTargetProfile('')
  }

  const searchUserByNickname = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault()

    // 해당 닉네임을 가진 회원의 UUID
    const res1 = await getUserByNickname(value, accessToken)

    // 회원 UUID로 대표 프로필 이미지 불러오기
    if (res1.result) {
      const targetUuid = res1.result.uuid
      setTargetUuid(res1.result.uuid)

      const res2 = await getMainProfileImageByUuid(targetUuid, accessToken)
      setTargetNickname(value)
      setTargetProfile(res2?.profileImageUrl ?? '')
    } else {
      setTargetNickname(res1.message)
    }
  }

  /** 친구 모달 띄우기 */
  const handleOpenProfile = () => {}

  return (
    <div className="h-14 mx-0 my-[26px]">
      <form className="flex" action="" onSubmit={searchUserByNickname}>
        <SearchInput
          className="h-14"
          placeholder="Search people or message"
          value={value}
          onChange={handleChange}
        />
        <button type="submit">검색</button>
      </form>
      <div>
        결과: <br />
        <Link href={`/profile/${targetUuid}`} onClick={handleOpenProfile}>
          {targetProfile && (
            <Image
              src={targetProfile}
              alt={targetNickname}
              width={40}
              height={40}
            />
          )}
          {targetNickname && targetNickname}
        </Link>
      </div>
    </div>
  )
}
