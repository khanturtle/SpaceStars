'use client'

import Image from 'next/image'

import React, { ChangeEvent, useState } from 'react'

import { SearchInput } from '@packages/ui'

import { getUuidByNickname } from '@/apis/getMember'
import { getMainProfileImageByUuid } from '@/apis/getProfileImage'
import Link from 'next/link'
import { defaultImage } from '@/store/defaultState'

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
  const [message, setMessage] = useState<string>('')

  const handleChange = async (e: ChangeEvent<HTMLInputElement>) => {
    setValue(e.target.value)
    setTargetNickname('')
    setTargetProfile('')
    setMessage('')
  }

  const searchUserByNickname = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault()

    try {
      let response

      // 해당 닉네임을 가진 회원의 UUID 찾기
      response = await getUuidByNickname(value, accessToken)

      if (!response) {
        throw new Error('Failed to getUuidByNickname')
      }

      if (response.result !== null) {
        const targetUuid = response.result.uuid!

        // 회원 UUID로 대표 프로필 이미지 가져오기
        response = await getMainProfileImageByUuid(targetUuid, accessToken)

        // state 저장
        setTargetNickname(value)
        setTargetUuid(targetUuid)
        setTargetProfile(response?.result?.profileImageUrl ?? defaultImage)
      } else {
        setMessage(response.message)
        setTargetNickname('')
        setTargetUuid('')
        setTargetProfile('')
      }
    } catch {
      // TODO: 서버 에러 경고
      setMessage('다시 시도해주세요.')

      setTargetNickname('')
      setTargetUuid('')
      setTargetProfile('')
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
        {message}
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
