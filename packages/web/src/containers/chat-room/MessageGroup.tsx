import Image from 'next/image'

import { useSession } from 'next-auth/react'

import { useEffect, useState } from 'react'

import { getMainProfileImageByUuid } from '@/apis/getProfileImage'
import { getProfileByUuid } from '@/apis/getAuth'

import { ChatMessageType } from '@/types/ChatType'
import { getConvertToKoreanHM } from '@/hooks/convertToLocaleTime'

import styles from './chat.module.css'

const UserProfile = ({ uuid }: { uuid: string }) => {
  const { data: session } = useSession()
  const [nickname, setNickname] = useState<string>()
  // TODO: defaultImage 저장하기
  const [profileImage, setProfileImage] = useState<string>('/defaultUrl')

  useEffect(() => {
    const fetchData = async () => {
      if (session) {
        const token = session?.user?.data.accessToken
        const profileData = await getProfileByUuid(uuid, token)
        const profileImage = await getMainProfileImageByUuid(uuid, token)

        setNickname(profileData?.nickname ?? 'user')
        setProfileImage(profileImage?.profileImageUrl ?? '/defaultUrl')
      }
    }
    fetchData()
  }, [])

  return (
    <div className={styles.profile}>
      <Image
        className="rounded-full"
        src={profileImage}
        alt={uuid}
        width={36}
        height={36}
      />
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
      {messages?.map((msg) => (
        <div
          key={msg.createdAt}
          className={`${styles['message-bubble']} ${styles[type]}`}
        >
          {msg.content}
        </div>
      ))}
      <span className={styles.time}>{time}</span>
    </div>
  )
}
