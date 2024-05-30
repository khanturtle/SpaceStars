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

const TeamList = () => {
  return <ul>리스트</ul>
}

TeamBox.TeamCardList = TeamCardList
TeamBox.TeamCardItem = TeamCardItem
TeamBox.TeamList = TeamList

export default TeamBox
