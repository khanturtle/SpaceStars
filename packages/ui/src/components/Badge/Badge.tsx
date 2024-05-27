import './Badge.css'

export interface BadgeProps {
  className?: string
  value?: string
  size: 'small' | 'large'
  type: 'game' | 'open' | 'closed'
}

const Badge = ({
  className,
  value,
  size = 'large',
  type = 'game',
}: BadgeProps) => {
  return (
    <div
      className={`badge-container badge--${size} badge--${type} ${className}`}
    >
      <span>{value}</span>
    </div>
  )
}

export default Badge
