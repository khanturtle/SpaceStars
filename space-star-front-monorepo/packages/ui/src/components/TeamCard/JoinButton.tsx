import './teamcard.css'
import { LockIcon } from '../Icons'

export interface JoinButtonProps
  extends React.ButtonHTMLAttributes<HTMLButtonElement> {
  className?: string
  isLocked?: boolean
  isFinished?: boolean
  buttonText?: string
  iconFill?: string
  onClick?: () => void
}

const JoinButton = ({
  className,
  isLocked = false,
  isFinished = false,
  buttonText,
  onClick,
  iconFill,
  ...props
}: JoinButtonProps) => {
  const buttonStyle = isFinished ? 'fin' : 'join'

  let text
  if (buttonText) {
    text = buttonText
  } else {
    text = isFinished ? '모집완료' : '참가하기'
  }

  return (
    <button
      type="button"
      onClick={onClick}
      className={`join-button ${buttonStyle} ${className}`}
      {...props}
    >
      <p>{text}</p>

      {isLocked && (
        <i>
          <LockIcon fill={iconFill} />
        </i>
      )}
    </button>
  )
}

export default JoinButton
