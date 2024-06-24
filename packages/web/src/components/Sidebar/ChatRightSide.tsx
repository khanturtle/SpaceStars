'use client'

import { useEffect, useState } from 'react'

import { useWebSocket } from '../providers/socket-provider'

import { getChatroomData, getTeamChatroomData } from '@/lib/getRoomDataByClient'

import FriendsList from '../Friends/FriendsList'

import styles from './Sidebar.module.css'
import { usePathname } from 'next/navigation'

interface AllUserType {
  index: number
  friendUuid: string
  profileImageUrl: string
  nickname: string
  status: boolean
  ownerStatus?: boolean
}

function updateMemberStatus(members: AllUserType[], onlineUsers: string[]) {
  return members.map((member) => {
    return {
      ...member,
      status: onlineUsers.includes(member.friendUuid),
    }
  })
}

export const ChatRightSide = ({
  roomNumber,
  token,
  roomType,
}: {
  roomNumber: string
  token: string
  roomType: 'one-to-one' | 'team'
}) => {
  const stompClient = useWebSocket()

  const [allUser, setAllUser] = useState<AllUserType[]>([])
  const [onlineUser, setOnlineUser] = useState([])

  const pathName = usePathname()

  /** 채팅방 유저 리스트 */
  useEffect(() => {
    const roomType = pathName.split('/').includes('group')
      ? 'team'
      : 'one-to-one'

    const fetchData = async () => {
      if (roomType === 'one-to-one') {
        const userList = await getChatroomData(roomNumber, token)
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
        const userList = await getTeamChatroomData(roomNumber, token)
        setAllUser(
          userList.map((item) => ({
            index: item.index,
            nickname: item.nickname,
            profileImageUrl: item.profileImageUrl,
            friendUuid: item.memberUuid,
            ownerStatus: item.ownerStatus,
            status: false,
          })),
        )
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
        `/sub/${roomType}/users/${roomNumber}`,
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
