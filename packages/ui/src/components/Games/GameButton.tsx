import Check from '../Icons/Check'
import './game.css'

type ItemType = {
  gameImage: string
  gameName: string
}

export interface GameButtonProps {
  className?: string
  isClicked?: boolean
  item: ItemType
  onClick?: () => void
}

const GameButton = ({
  className,
  onClick,
  isClicked = false,
  item,
  ...props
}: GameButtonProps) => {
  return (
    <button
      type="button"
      className={`game-button-container ${isClicked ? 'clicked' : ''} ${className}`}
      onClick={onClick}
      {...props}
    >
      <div className="button-image">
        <div className="image-container">
          <img src={item.gameImage} alt={item.gameName} />
        </div>
      </div>
      <p className="button-text">{item.gameName}</p>
      <div className="button-bottom" />

      {isClicked && (
        <div className="button-clicked">
          <Check fill="#F7F7F7" />
        </div>
      )}
    </button>
  )
}

export default GameButton
