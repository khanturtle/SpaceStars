'use client'

import Image from 'next/image'

import { useContext, useEffect, useState } from 'react'

import { Ellipsis, Phone } from 'lucide-react'

import { getGameById } from '@/apis/getGame'
import {
  getGroupChatInfo,
  getTeamChatRoomsMember,
} from '@/apis/getGroupChatByClient'
import { ModalContext } from '@/components/providers/modal-provider'
import { GameType, GroupChatInfo } from '@/types/type'

import styles from './chat.module.css'

export default function GroupChatHeader({
  roomNumber,
  token,
}: {
  roomNumber: string
  token: string
}) {
  const [roomInfo, setRoomInfo] = useState<GroupChatInfo>()
  const [gameInfo, setGameInfo] = useState<GameType>()
  const [memberInfo, setMemberInfo] = useState()

  const { openModal } = useContext(ModalContext)

  useEffect(() => {
    const fetchData = async () => {
      const roomData = await getGroupChatInfo(roomNumber, token)
      const gameData = await getGameById(roomData.gameId)
      const memberData = await getTeamChatRoomsMember(roomNumber, token)

      setRoomInfo(roomData)
      if (gameData) {
        setGameInfo(gameData)
      }
      setMemberInfo(memberData)
    }

    fetchData()
  }, [roomNumber])

  // TODO: 보이스 연결
  const handleCall = () => {}

  // TODO: 채팅방 정보
  const handleMore = () => {
    openModal(<div className={styles.roomPopup}></div>)
  }

  return (
    <div className={styles.header}>
      {gameInfo && (
        <Image
          src={gameInfo.gameImage}
          alt={gameInfo.gameName}
          width={48}
          height={48}
          className={styles.image}
        />
      )}
      <div className={styles.roomInfo}>
        <h3>{roomInfo?.roomName ?? ''}</h3>
        <p>{roomInfo?.memo ?? ''}</p>
      </div>

      <div className="flex-1" />

      <div className={styles.icons}>
        <button type="button" onClick={handleCall}>
          <Phone stroke="#869AA9" />
        </button>

        <button type="button" onClick={handleMore}>
          <Ellipsis stroke="#869AA9" />
        </button>
      </div>
    </div>
  )
}
