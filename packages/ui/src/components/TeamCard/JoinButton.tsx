import './teamcard.css'
import { LockIcon } from '../Icons'

export interface JoinButtonProps
  extends React.ButtonHTMLAttributes<HTMLButtonElement> {
  className?: string
  isLocked?: boolean
  isFinished?: boolean
  onClick?: () => void
}

const JoinButton = ({
  className,
  isLocked = false,
  isFinished = false,
  onClick,
  ...props
}: JoinButtonProps) => {
  const buttonStyle = isFinished ? 'fin' : 'join'
  const buttonText = isFinished ? '모집완료' : '참가하기'
  const iconFill = isFinished ? '#a8a8a8' : '#7d12ff'

  return (
    <button
      type="button"
      onClick={onClick}
      className={`join-button ${buttonStyle} ${className}`}
      {...props}
    >
      <p>{buttonText}</p>

      {isLocked && (
        <i>
          <LockIcon fill={iconFill} />
        </i>
      )}
    </button>
  )
}

export default JoinButton
