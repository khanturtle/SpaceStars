import Image from 'next/image'

import { useSession } from 'next-auth/react'

import { useEffect, useState } from 'react'

import { getMainProfileImageByUuid } from '@/apis/getProfileImage'
import { getProfileByUuid } from '@/apis/getAuth'

import { ChatMessageType } from '@/types/ChatType'
import { getConvertToKoreanHM } from '@/hooks/convertToLocaleTime'
import { defaultImage } from '@/store/defaultState'

import styles from './chat.module.css'

const UserProfile = ({ uuid }: { uuid: string }) => {
  const { data: session } = useSession()
  const [nickname, setNickname] = useState<string>()
  const [profileImage, setProfileImage] = useState<string>(defaultImage)

  useEffect(() => {
    const fetchData = async () => {
      if (session) {
        const token = session?.user?.data.accessToken
        const profileData = await getProfileByUuid(uuid, token)
        const profileImage = await getMainProfileImageByUuid(uuid, token)

        setNickname(profileData?.result.nickname ?? 'user')
        setProfileImage(profileImage?.result?.profileImageUrl ?? defaultImage)
      }
    }
    fetchData()
  }, [])

  return (
    <div
      className={`${styles.profile} mr-[15px] relative border-2 border-solid`}
    >
      <div className="w-[36px] h-[36px] rounded-full relative">
        <Image
          className="object-cover object-center rounded-full"
          src={profileImage}
          alt={uuid}
          fill
          sizes="(max-width: 768px) 100vw, (max-width: 1200px) 50vw, 33vw"
        />
      </div>
      <p>{nickname}</p>
    </div>
  )
}

export default function MessageGroup({
  messages,
  UUID,
}: {
  messages: ChatMessageType[]
  UUID: string
}) {
  const type = messages && messages[0].senderUuid === UUID ? 'sent' : 'received'
  const time = getConvertToKoreanHM(messages[messages.length - 1].createdAt)

  return (
    <div className={`${styles['message-group']} ${styles[type]}`}>
      {type === 'received' && <UserProfile uuid={messages[0].senderUuid} />}
      {messages?.map((msg, index) => (
        <div
          key={msg.createdAt + index}
          className={`${styles['message-bubble']} ${styles[type]}`}
        >
          {msg.content}
        </div>
      ))}
      <span className={styles.time}>{time}</span>
    </div>
  )
}
