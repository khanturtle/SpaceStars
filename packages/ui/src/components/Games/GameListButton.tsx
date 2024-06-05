import Check from '../Icons/Check'
import './game.css'

type ItemType = {
  gameImage: string
  gameLogo: string
  gameName: string
}

export interface GameButtonProps {
  className?: string
  isClicked?: boolean
  item: ItemType
  onClick?: () => void
}

const GameListButton = ({
  className,
  onClick,
  isClicked = false,
  item,
  ...props
}: GameButtonProps) => {
  return (
    <button
      type="button"
      className={`relative game-list-button 
      ${isClicked ? 'clicked' : ''} ${className}`}
      onClick={onClick}
      style={{
        backgroundImage: `url(${item.gameImage})`,
        backgroundSize: 'cover',
        backgroundPosition: 'center',
      }}
      {...props}
    >
      <div className="z-10 flex items-center justify-center w-full h-full ">
        <div className="game-logo">
          <img
            className="object-cover w-full h-full"
            src={item.gameLogo}
            alt={item.gameName}
          />

          {isClicked && (
            <i className="checked">
              <Check width="8" />
            </i>
          )}
        </div>
      </div>
    </button>
  )
}

export default GameListButton
