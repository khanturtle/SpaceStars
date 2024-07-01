'use client'

import { GameTypes } from '@/types/type'
import { useRouter } from 'next/navigation'
import { useEffect, useRef, useState } from 'react'
import styles from './dashboard.module.css'

const GameSelector = ({ games }: { games: GameTypes[] }) => {
  const router = useRouter()
  const handleClick = (game: GameTypes) => {
    router.push(`/dashboard/team-list?game=${game.gameId}`)
  }

  const containerRef = useRef<HTMLDivElement>(null)
  const [visibleGames, setVisibleGames] = useState<GameTypes[]>([])

  useEffect(() => {
    const updateVisibleGames = () => {
      if (containerRef.current) {
        const containerWidth = containerRef.current.offsetWidth
        const gameWidth = 140 // 게임 버튼 너비 + gap
        const visibleCount = Math.floor(containerWidth / gameWidth)
        setVisibleGames(games.slice(0, visibleCount))
      }
    }

    updateVisibleGames()
    window.addEventListener('resize', updateVisibleGames)

    return () => {
      window.removeEventListener('resize', updateVisibleGames)
    }
  }, [])

  // TODO: 라이트모드 색 수정
  return (
    <div className={styles.container} ref={containerRef}>
      <div className={styles.scrollContainer}>
        {visibleGames.map((game) => (
          <button key={game.index} className={styles.gameButton}>
            <img
              src={game.gameLogoImage}
              alt={game.gameName}
              className={styles.gameIcon}
            />
            <span className={styles.gameName}>{game.gameNameKor}</span>
          </button>
        ))}
      </div>
    </div>
  )
}

export default GameSelector
