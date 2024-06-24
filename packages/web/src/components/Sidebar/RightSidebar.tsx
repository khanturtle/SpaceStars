'use client'

import { usePathname } from 'next/navigation'

import { useEffect, useState } from 'react'

import { useWebSocket } from '../providers/socket-provider'

import { friendsWithBasicDataType } from '@/lib/getFriendsData'
import { getChatroomData } from '@/lib/getRoomDataByClient'

import FriendsList from '../Friends/FriendsList'

import styles from './Sidebar.module.css'

function updateMemberStatus(members: AllUserType[], onlineUsers: string[]) {
  return members.map((member) => {
    return {
      ...member,
      status: onlineUsers.includes(member.friendUuid),
    }
  })
}

interface AllUserType {
  index: number
  friendUuid: string
  profileImageUrl: string
  nickname: string
  status: boolean
}

const ChatRightSide = ({
  roomNumber,
  token,
}: {
  roomNumber: string
  token: string
}) => {
  const stompClient = useWebSocket()

  const [allUser, setAllUser] = useState<AllUserType[]>([])
  const [onlineUser, setOnlineUser] = useState([])

  /** 채팅방 유저 리스트 */
  useEffect(() => {
    const fetchData = async () => {
      const userList = await getChatroomData(roomNumber, token)
      if (userList) {
        setAllUser(
          userList.map((item) => ({
            index: item.index,
            nickname: item.nickname,
            profileImageUrl: item.profileImageUrl,
            friendUuid: item.memberUuid,
            status: false,
          })),
        )
      } else {
        setAllUser([])
      }
    }
    fetchData()
  }, [roomNumber])

  useEffect(() => {
    const updateUser = updateMemberStatus(allUser, onlineUser)
    setAllUser(updateUser)
  }, [onlineUser])

  /** 채팅 소켓 연결 */
  useEffect(() => {
    if (stompClient) {
      /** 채팅방 접속자 */
      const subscription = stompClient.subscribe(
        `/sub/one-to-one/users/${roomNumber}`,
        (frame) => {
          const memberUuids = JSON.parse(frame.body).memberUuids
          setOnlineUser(memberUuids)
        },
        {},
      )

      /**  구독 해제 */
      return () => {
        subscription.unsubscribe()
      }
    }
    return undefined
  }, [stompClient, roomNumber])

  return (
    <section>
      <div className={styles['side-title']}>Chat Room</div>
      <FriendsList>
        {allUser &&
          allUser.map((item) => (
            <FriendsList.UserItem key={item.index} item={item}>
              {/* FIXME: status 확인 */}
              {/* <FriendsList.Status status={item.status} /> */}
            </FriendsList.UserItem>
          ))}
      </FriendsList>
    </section>
  )
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
  token,
  isChatPage,
  friendsList,
}: {
  token: string
  isChatPage: boolean
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
        {isChatPage ? (
          <ChatRightSide roomNumber={roomNumber} token={token} />
        ) : (
          <DefaultRightSide friendsList={friendsList} />
        )}
      </div>
    </section>
  )
}
