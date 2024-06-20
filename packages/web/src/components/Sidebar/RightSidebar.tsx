import Image from 'next/image'

import { FriendsListType } from '@/types/type'

import styles from './Sidebar.module.css'
import FriendsList from '../Friends/FriendsList'

interface friendsWithBasicDataType extends FriendsListType {
  profileImageUrl: string
  nickname: string
}

export default function RightSidebar({
  friendsList,
}: {
  friendsList: friendsWithBasicDataType[]
}) {
  // FIXME: 이걸 같이 관리하는게 네브바에 있어야 함. 열고 닫을 수 있게
  // const [rightSide, setRightSide] = useState(false)

  // const pathName = usePathname()
  // const pathParts = pathName.split('/')

  // 채팅방
  // if (pathName.includes('chat') && pathParts.length === 4) {
  //   return (
  //     <section
  //       className={`${styles['right-side']} ${rightSide && `${styles.active}`}`}
  //     >
  //       채팅 참여자
  //     </section>
  //   )
  // }

  // 기본 친구 리스트
  return (
    <section
      className={`${styles['right-side']}`}
      // className={`${styles['right-side']} ${rightSide && `${styles.active}`}`}
    >
      <div className={styles['side-wrapper']}>
        {/* FIXME: 여기서 채팅이랑 분기하는게 나을듯 */}
        <section>
          <div className={styles['side-title']}>Friends</div>
          <FriendsList items={friendsList} />
        </section>
      </div>
    </section>
  )
}
