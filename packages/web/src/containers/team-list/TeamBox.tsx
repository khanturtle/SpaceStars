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

const TeamCardList = ({ children }: { children?: React.ReactNode }) => {
  return <ul className={styles['team-card-container']}>{children}</ul>
}

const TeamCardItem = ({
  isFinished,
  gameData,
  roomType,
  users,
  title,
  joinButton,
}: {
  isFinished?: boolean
  gameData?: React.ReactNode
  roomType?: React.ReactNode
  users?: React.ReactNode
  title?: React.ReactNode
  joinButton?: React.ReactNode
}) => {
  const TYPE = isFinished ? 'finished' : 'ongoing'

  return (
    <li className={`${styles['card-list']} ${styles[TYPE]}`}>
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
}

const TeamList = ({ children }: { children: React.ReactNode }) => {
  return <ul className={styles['list-container']}>{children}</ul>
}
const TeamItem = ({
  isFinished,
  gameData,
  roomType,
  users,
  title,
  joinButton,
}: {
  isFinished?: boolean
  gameData?: React.ReactNode
  roomType?: React.ReactNode
  users?: React.ReactNode
  title?: React.ReactNode
  joinButton?: React.ReactNode
}) => {
  const TYPE = isFinished ? 'finished' : 'ongoing'

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
TeamBox.TeamList = TeamList
TeamBox.TeamItem = TeamItem

export default TeamBox
