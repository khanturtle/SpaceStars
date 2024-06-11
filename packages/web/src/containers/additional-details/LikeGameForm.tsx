import { GameButton } from '@packages/ui'

import { ItemType, GAME_DATA } from './state'

export default function LikeGameForm({
  selectedGames,
  onClick,
  className,
  ...props
}: {
  selectedGames: ItemType[]
  onClick: (game: ItemType) => void
  className?: string
}) {
  return (
    <section
      className={`flex flex-row flex-wrap justify-between gap-[17px_0] mb-[47px] ${className}`}
      {...props}
    >
      {GAME_DATA.map((item) => (
        <GameButton
          key={item.index}
          item={item}
          isClicked={selectedGames.includes(item)}
          onClick={() => onClick(item)}
        />
      ))}
    </section>
  )
}
