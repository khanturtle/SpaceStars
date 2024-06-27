'use client'

import { usePathname } from 'next/navigation'

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
      <FriendsList items={friendsList} />
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
  const pathParts = pathName.split('/')

  const roomNumber = pathParts.at(-1) ?? ''

  return (
    <section
      className={`${styles['right-side']}`}
    >
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
