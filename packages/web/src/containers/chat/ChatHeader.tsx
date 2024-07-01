'use client'

import Image from 'next/image'
import { useRouter } from 'next/navigation'

import {  useEffect, useState } from 'react'

import { Ellipsis, Phone } from 'lucide-react'

import { ArrowIcon } from '@packages/ui'

import { getChatroomData, MemberInfoType } from '@/lib/getRoomDataByClient'

import styles from './chat.module.css'

export default function ChatHeader({
  roomNumber,
  token,
  UUID,
}: {
  roomNumber: string
  token: string
  UUID: string
}) {
  const router = useRouter()
  const [data, setData] = useState<MemberInfoType[]>()
  const [other, setOther] = useState<MemberInfoType>()

  useEffect(() => {
    const fetchData = async () => {
      const roomData = await getChatroomData(roomNumber, token)
      if (roomData) {
        setData(roomData)
      }
    }

    fetchData()
  }, [roomNumber])

  useEffect(() => {
    const otherData = data?.find((item) => item.memberUuid !== UUID)
    setOther(otherData)
  }, [data])

  const handleCall = () => {}

  const handleMore = () => {}

  return (
    <div className={`${styles.header} chat-header`}>
      <button
        className={styles.back}
        onClick={() => {
          router.push('/dashboard/chat')
        }}
      >
        <ArrowIcon
          type="left"
          fill="var(--color-icon)"
          width="20"
          height="20"
        />
      </button>

      {other && (
        <>
          <Image
            src={other.profileImageUrl}
            alt={other.profileImageUrl}
            width={48}
            height={48}
            className={styles.image}
          />
          <div className={styles.roomInfo}>
            <h3>{other?.nickname ?? ''}</h3>
          </div>
        </>
      )}

      <div className="flex-1" />

      <div className={styles.icons}>
        <button type="button" onClick={handleCall}>
          <Phone stroke="#869AA9" />
        </button>

        <button type="button" onClick={handleMore}>
          <Ellipsis stroke="#869AA9" />
        </button>
      </div>
    </div>
  )
}
