'use client'

import { usePathname } from 'next/navigation'
import Link from 'next/link'

import { useEffect, useState } from 'react'

import { friendsWithBasicDataType } from '@/lib/getFriendsData'

import FriendsList from '../Friends/FriendsList'
import { ChatRightSide } from './ChatRightSide'

import styles from './Sidebar.module.css'

const DefaultRightSide = ({
  friendsList,
}: {
  friendsList: friendsWithBasicDataType[]
}) => {
  return (
    <section>
      <div className={styles['side-title']}>Friends</div>
      {friendsList.length > 0 ? (
        <FriendsList items={friendsList} />
      ) : (
        <div className={styles.friend}>
          <h4 className={styles['friend-title']}>아직 친구가 없어요...</h4>
          <p className={styles['friend-text']}>
            친구가 생기면 여기에 표시돼요!
          </p>
        </div>
      )}
    </section>
  )
}

export default function RightSidebar({
  token,
  isChatPage,
  isGroupChatPage,
  friendsList,
}: {
  token: string
  isChatPage: boolean
  isGroupChatPage: boolean
  friendsList: friendsWithBasicDataType[]
}) {
  const pathName = usePathname()
  const [roomNumber, setRoomNumber] = useState<string>('')

  useEffect(() => {
    const pathParts = pathName.split('/')
    setRoomNumber(pathParts.at(-1) ?? '')
  }, [pathName])

  return (
    <section className={`${styles['right-side']}`}>
      <div className={styles['side-wrapper']}>
        {isGroupChatPage ? (
          <ChatRightSide
            roomNumber={roomNumber}
            token={token}
            roomType="team"
          />
        ) : isChatPage ? (
          <ChatRightSide
            roomNumber={roomNumber}
            token={token}
            roomType="one-to-one"
          />
        ) : (
          <DefaultRightSide friendsList={friendsList} />
        )}
      </div>
    </section>
  )
}
