import styles from './teamList.module.css'

const TeamBox = ({
  children,
  className,
}: {
  children?: React.ReactNode
  className?: string
}) => {
  return <section className={className}>{children}</section>
}

const TeamCardList = ({
  children,
  type = 'list',
}: {
  children?: React.ReactNode
  type?: 'card' | 'list'
}) => {
  const className =
    type === 'card' ? styles['card-container'] : styles['list-container']

  return <ul className={className}>{children}</ul>
}

const TeamCardItem = ({
  isFinished,
  gameData,
  roomType,
  users,
  title,
  joinButton,
  type = 'list',
}: {
  isFinished?: boolean
  gameData?: React.ReactNode
  roomType?: React.ReactNode
  users?: React.ReactNode
  title?: React.ReactNode
  joinButton?: React.ReactNode
  type?: 'card' | 'list'
}) => {
  const TYPE = isFinished ? 'finished' : 'ongoing'
  const isCardVariant = type === 'card'

  if (isCardVariant)
    return (
      <li className={`${styles.card} ${styles[TYPE]}`}>
        <div className="relative flex flex-row items-center w-full">
          <div className="flex gap-[5px]">
            {gameData}
            {roomType}
          </div>
          {users}
        </div>
        {title}
        <div>{joinButton}</div>
      </li>
    )
  return (
    <li className={`${styles.list} ${styles[TYPE]}`}>
      <div className="w-[380px]">{title}</div>
      <div className="flex gap-[5px] flex-1">
        {gameData}
        {roomType}
      </div>

      <div className="relative flex items-center justify-between w-[228px]">
        <div className="relative">{users}</div>
        <div>{joinButton}</div>
      </div>
    </li>
  )
}

TeamBox.TeamCardList = TeamCardList
TeamBox.TeamCardItem = TeamCardItem

export default TeamBox
