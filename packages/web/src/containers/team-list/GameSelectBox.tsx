'use client'

import { useRouter } from 'next/navigation'

import { GameListButton } from '@packages/ui'

import styles from './teamList.module.css'

import { GameType } from '@/apis/game'

export default function GameSelectBox({
  games,
  searchParams,
}: {
  games: GameType[]
  searchParams: { [key: string]: string }
}) {
  const TEMP_ITEM = {
    gameImage:
      'https://s3-alpha-sig.figma.com/img/1d3e/76db/bf5d355441d7035183675e2b076a8840?Expires=1718582400&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=Fe6jr1yIJxbZ9KiReWFCvIUoOMWMLPSgR0iiRlswZHLMmhp3pVvdNpd10y5GT0euPGki03NiwTUt~7Cm4i3n4G2NXcI5rRiSYqvqcKXTJQLdeij~taiC5B5t6evckLEjeoEF0XEQDOziGvZlvKOTljrMSKeO3nYy7QDsTzgtlBSZk4OWItdGGm7hJTdVMhqz96kGLb8xYhjkfvwXgbtNMyarPVcyTlIKylux9iTzB9vCUNGviUCqXVasEpjGnAB~FVIs7Nr193ya8SpwBoxfbI04Jvv9rHlN0astfIb~JgK4mTTVdAss0l4WKIwJAhvBWMJnkFbCM7nSPIYgq~g06A__',
    gameLogo:
      'https://s3-alpha-sig.figma.com/img/434b/06d6/0bfc112079bf2c0b0d74da38e1b5279c?Expires=1718582400&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=lYs-7hAvk5rDeTC1yldjP8Bvx5EnPRjCCxKiska81F3hUcG1K0kULMbhkbmGd6yxhddiX67NS7RrJr4ETLKw2kY5B3fVqBwPsxYC7lFxJlPjp7DPwO0Z94rJJtQN1FCxuHEg17StoLvOJkzT624-u2o3yzSOVGLfbqvDF8iBqr1z4R~4h3czj5nQ8VmAc82yN32QeXkSk~3xM~cFWhb5m~GCSI9KPGgPDjRwHtB7W9ha3agzbnD3huTQNG07V~jdNt0vRXWaGd8NywnCE3eLgicMkYrPM58UX12lfmbKtADKmmPGwnJzn1zJl2JtgzAiqU4iKWhcWn5qLWeu8lvEqg__',
    gameName: '리그오브레전드',
  }

  const router = useRouter()

  const handleGame = (item: GameType) => {
    const currentParams = new URLSearchParams(searchParams)
    currentParams.set('game', item.gameName)
    router.push(`?${currentParams.toString()}`)
  }
  console.log(games)

  return (
    // TODO: 터치 스크롤 구현
    <div className={`${styles.gameList}`}>
      {games &&
        games.map((game) => (
          <div key={game.index}>
            <GameListButton
              // FIXME: game 데이터 수정
              item={TEMP_ITEM}
              onClick={() => handleGame(game)}
              isClicked={searchParams.game === game.gameName}
            />
          </div>
        ))}
    </div>
  )
}
