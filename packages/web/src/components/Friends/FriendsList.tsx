import { TmpFriendType } from '@/apis/friends'
import Image from 'next/image'

import styles from './friends.module.css'

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

const OnlineFriends = ({ friends }: { friends: TmpFriendType[] }) => {
  // TODO: 가로 터치 스크롤
  return (
    <ul className={styles.ul}>
      {friends.map((item) => (
        <li key={item.index} className="flex flex-col items-center gap-2">
          <div className="relative w-12 h-12">
            <Image
              className="w-12 h-12 rounded-full"
              width={48}
              height={48}
              src={item.image_url}
              alt={item.name}
            />
            <div className="w-3 h-3 shrink-0 bg-[color:var(--online,#3bcd23)] border border-[color:var(--White-50,#fff)] absolute rounded-[50%] right-0 bottom-0" />
          </div>
          <p className="w-11 text-ellipsis whitespace-nowrap overflow-hidden text-[#869aa9] text-center text-xs not-italic font-normal leading-[normal]">
            {item.name}
          </p>
        </li>
      ))}
    </ul>
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
