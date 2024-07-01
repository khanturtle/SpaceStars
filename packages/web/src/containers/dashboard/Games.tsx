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

  return (
    <div className={`${styles.outerContainer} shadow-md`}>
      <h2 className="text-[color:var(--text-title)] mb-4">게임 팀원 모집</h2>
      <div className={styles.gridContainer}>
        {games.slice(0, 6).map((game) => (
          <button
            type="button"
            key={game.index}
            className={styles.gameButton}
            onClick={() => handleClick(game)}
          >
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
