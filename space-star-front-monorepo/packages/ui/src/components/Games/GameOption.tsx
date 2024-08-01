import CirclePlusIcon from '../Icons/CirclePlus'
import './game.css'

type GameOptionType = {
  index: number
  position_id?: number
  position_name?: string
  position_name_kor?: string
  position_image?: string
}

export interface GameOptionProps {
  className?: string
  gameImage?: string
  gameName?: string
  options?: GameOptionType[]
}

const GameOption = ({
  className,
  gameImage,
  gameName,
  options,
}: GameOptionProps) => {
  return (
    <div className={`game-option-container ${className}`}>
      <div className="relative w-[80px] max-h-[52px]">
        <img
          className="object-contain w-full h-full"
          src={gameImage}
          alt={gameName}
        />
      </div>

      <div className="flex items-start gap-2.5">
        {/* 해당 게임에 맞는 옵션 선택하기 */}
        {options &&
          options.map((_, index) => (
            <button key={index} className="w-[40px] h-[40px] relative">
              <i>
                <CirclePlusIcon />
              </i>
            </button>
          ))}
      </div>
    </div>
  )
}

export default GameOption
