import styles from './friends.module.css'
import { OnlineFriends } from './OnlineFriends'

export default function FriendsList({
  title = 'Friends',
  children,
}: {
  title?: string
  children?: React.ReactNode
}) {
  return (
    <div className="mb-9">
      <h3 className="text-[#161616] text-lg not-italic font-medium leading-[normal] mb-6">
        {title}
      </h3>

      {children}
    </div>
  )
}

// TODO: 오른쪽 사이드에 보이는 모든 친구 리스트
const AllFriends = () => {
  return (
    <ul className={styles.ul}>
      {/* {tmpFriends.map((item) => (
        <li>
          <div className="flex items-center gap-2" />
        </li>
      ))} */}
    </ul>
  )
}

FriendsList.OnlineFriends = OnlineFriends
FriendsList.AllFriends = AllFriends
