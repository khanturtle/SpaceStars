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
  isTeamChatPage,
  friendsList,
}: {
  token: string
  isChatPage: boolean
  isTeamChatPage: boolean
  friendsList: friendsWithBasicDataType[]
}) {
  // FIXME: 이걸 같이 관리하는게 네브바에 있어야 함. 열고 닫을 수 있게 => 친구 아이콘으로 하기
  // const [rightSide, setRightSide] = useState(false)

  const pathName = usePathname()
  const pathParts = pathName.split('/')

  const roomNumber = pathParts.at(-1) ?? ''

  return (
    <section
      className={`${styles['right-side']}`}
      // className={`${styles['right-side']} ${rightSide && `${styles.active}`}`}
    >
      <div className={styles['side-wrapper']}>
        {isTeamChatPage ? (
          <ChatRightSide roomNumber={roomNumber} token={token} type="team" />
        ) : isChatPage ? (
          <ChatRightSide roomNumber={roomNumber} token={token} type="chat" />
        ) : (
          <DefaultRightSide friendsList={friendsList} />
        )}
      </div>
    </section>
  )
}
