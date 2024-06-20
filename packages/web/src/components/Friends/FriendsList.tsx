import Image from 'next/image'
import Link from 'next/link'

import { FriendsListType } from '@/types/type'

interface friendsWithBasicDataType extends FriendsListType {
  profileImageUrl: string
  nickname: string
}

const FriendsList = ({ items }: { items: friendsWithBasicDataType[] }) => {
  return (
    <ul>
      {items &&
        items.map((item: friendsWithBasicDataType) => (
          <UserItem key={item.index} item={item} />
        ))}
    </ul>
  )
}

const UserItem = ({
  item,
  children,
}: {
  item: friendsWithBasicDataType
  children?: React.ReactNode
}) => {
  const status = true

  return (
    <li className="flex items-center mb-[18px]">
      <Link
        href={`/profile/${item.friendUuid}`}
        className="flex items-center w-full"
      >
        <div
          className={`w-[45px] h-[45px] relative mr-[15px] rounded-[50%] border-2 border-solid ${status ? 'border-[color:var(--Main-Color-1,#6a64e9)]' : 'border-[color:var(--Stroke,#ddd)]'}`}
        >
          <Image
            src={item.profileImageUrl}
            alt={item.nickname}
            fill
            className="object-cover object-center rounded-full"
          />
        </div>

        <div className="flex items-center flex-1">
          <p className="text-[color:var(--Text-100,#1a1a1a)] text-sm not-italic font-medium leading-[normal] flex-1 text-ellipsis overflow-hidden">
            {item.nickname}
          </p>
        </div>

        {children}
      </Link>
    </li>
  )
}

const Status = ({ status }: { status: boolean }) => {
  return (
    <div
      className={`w-2 h-2 ml-2 rounded-[50%] ${status ? 'bg-[#7fd222]' : 'bg-[#606a8d]'}`}
    />
  )
}

UserItem.Status = Status

export default FriendsList
