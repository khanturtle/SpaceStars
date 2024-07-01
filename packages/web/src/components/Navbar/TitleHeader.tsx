import styles from './navbar.module.css'

export default function TitleHeader({
  title,
  description,
  type,
}: {
  title: string
  description?: string
  type: 'CHAT' | 'DESC'
}) {
  if (type === 'CHAT') {
    return (
      <div className="w-[380px] pl-[50px]">
        <h2 className="text-[color:var(--text-title)] text-4xl not-italic font-medium leading-[normal]">
          {title}
        </h2>
      </div>
    )
  }
  if (type === 'DESC') {
    return (
      <div className={`flex-1 ${styles['nav-header']}`}>
        <h3 className="text-[color:var(--nav-title)] text-4xl not-italic font-bold leading-[normal]">
          {title}
        </h3>
        <p className="text-[color:var(--text-title)] text-xs not-italic font-medium leading-[normal]">
          {description}
        </p>
      </div>
    )
  }
  return null
}
