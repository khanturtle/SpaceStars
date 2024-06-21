'use client'

import { usePathname } from 'next/navigation'

import { useEffect } from 'react'

import { friendsWithBasicDataType } from '@/lib/getFriendsData'
import { MemberInfoType } from '@/lib/getChatroomData'

import FriendsList from '../Friends/FriendsList'

import styles from './Sidebar.module.css'

const ChatRightSide = ({ roomNumber }: { roomNumber: string }) => {
  return <section>ㅇㅅㅇ</section>
}

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
  isChatPage,
  friendsList,
  memberInfos,
}: {
  isChatPage: boolean
  friendsList: friendsWithBasicDataType[]
  memberInfos: MemberInfoType[]
}) {
  console.log(memberInfos)
  // FIXME: 이걸 같이 관리하는게 네브바에 있어야 함. 열고 닫을 수 있게 => 친구 아이콘으로 하기
  // const [rightSide, setRightSide] = useState(false)

  const pathName = usePathname()
  const pathParts = pathName.split('/')

  useEffect(() => {}, [pathName])

  const roomNumber = pathParts.at(-1) ?? ''

  return (
    <section
      className={`${styles['right-side']}`}
      // className={`${styles['right-side']} ${rightSide && `${styles.active}`}`}
    >
      <div className={styles['side-wrapper']}>
        {/* FIXME: 여기서 채팅이랑 분기하는게 나을듯 */}
        {isChatPage ? (
          <ChatRightSide roomNumber={roomNumber} />
        ) : (
          <DefaultRightSide friendsList={friendsList} />
        )}
      </div>
    </section>
  )
}
