import './teamcard.css'

export interface CardTitleProps {
  className?: string
  imageUrl?: string
  title?: string
  description?: string
  descSize?: 's' | 'm'
}

const CardTitle = ({
  className,
  imageUrl,
  title,
  description,
  descSize = 'm',
}: CardTitleProps) => {
  const descriptionStyle =
    descSize == 'm'
      ? 'game-list-card-description'
      : 'game-list-card-description--small'
  return (
    <div
      className={`flex items-center justify-start gap-[11px] h-11 ${className}`}
    >
      <img
        className="relative w-11 h-full aspect-[1] rounded-full"
        src={imageUrl}
        alt="room_image"
      />

      <div className="flex flex-col items-start justify-start gap-0.5 w-full">
        <h3 className="block w-full overflow-hidden break-all whitespace-nowrap text-ellipsis game-list-card-title">
          {title}
        </h3>
        <p
          className={`block w-full overflow-hidden break-all whitespace-nowrap text-ellipsis ${descriptionStyle}`}
        >
          {description}
        </p>
      </div>
    </div>
  )
}

export default CardTitle
