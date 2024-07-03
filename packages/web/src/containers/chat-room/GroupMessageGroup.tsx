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
    <div className={styles.profile}>
      <div className="relative w-[36px] h-[36px] rounded-full">
        <Image
          className="object-cover object-center mr-1 rounded-full"
          src={profileImage}
          alt={uuid}
          width={0}
          height={0}
          sizes="100vw"
          style={{ width: '100%', height: 'auto', aspectRatio: 1 }}
        />
      </div>
      <p className="text-[color:var(--text)]">{nickname}</p>
    </div>
  )
}

export default function GroupMessageGroup({
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
