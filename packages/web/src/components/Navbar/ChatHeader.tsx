'use client'

import { usePathname } from 'next/navigation'
import { useSession } from 'next-auth/react'

import { useEffect, useState } from 'react'

import { getGameById } from '@/apis/getGame'
import {
  getGroupChatInfo,
  getTeamChatRoomsMember,
} from '@/apis/getGroupChatByClient'

import { GameType, GroupChatInfo } from '@/types/type'
import { getChatroomData, getTeamChatroomData } from '@/lib/getRoomDataByClient'

import styles from './navbar.module.css'

export default function ChatHeader() {
  const { data: session } = useSession()

  // 현재 채팅방
  const pathName = usePathname().split('/')
  const currentRoomNumber = pathName[pathName.length - 1]

  const [roomInfo, setRoomInfo] = useState<GroupChatInfo>()
  const [gameInfo, setGameInfo] = useState<GameType>()

  useEffect(() => {
    const isLoby = currentRoomNumber === 'chat'
    if (isLoby) {
      return
    }

    const isGroup = pathName.includes('group')

    const fetchData = async () => {
      const token = session?.user?.data.accessToken
      if (!token) {
        return
      }

      if (isGroup) {
        const roomData = await getGroupChatInfo(currentRoomNumber, token)

        setRoomInfo(roomData)

        const gameData = await getGameById(roomData?.gameId)
        if (gameData) {
          setGameInfo(gameData)
        }
        const memberData = await getTeamChatroomData(currentRoomNumber, token)
      } else {
        const memberData = await getChatroomData(currentRoomNumber, token)
      }
    }

    fetchData()
  }, [currentRoomNumber])

  return (
    <div className="flex flex-row flex-1">
      <div className="w-[380px] pl-[50px]">
        <h2 className="text-[color:var(--text-title)] text-4xl not-italic font-medium leading-[normal]">
          Chat
        </h2>
      </div>

      <div>
        <h3 className="text-[color:var(--text-title)]">{roomInfo?.gameId}</h3>
      </div>
    </div>
  )
}
