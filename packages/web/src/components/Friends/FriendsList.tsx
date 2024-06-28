import Image from 'next/image'
import Link from 'next/link'

import { friendsWithBasicDataType } from '@/lib/getFriendsData'

const FriendsList = ({
  items,
  children,
}: {
  items?: friendsWithBasicDataType[]
  children?: React.ReactNode
}) => {
  return (
    <ul>
      {items &&
        items.map((item: friendsWithBasicDataType) => (
          <UserItem key={item.index} item={item} />
        ))}
      {children && children}
    </ul>
  )
}

export interface UserItemType extends friendsWithBasicDataType {
  status?: boolean
}

const UserItem = ({
  item,
  children,
}: {
  item: UserItemType
  children?: React.ReactNode
}) => {
  const status = item.status ?? false

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
          <p className="text-[color:var(--text)] text-sm not-italic font-medium leading-[normal] flex-1 text-ellipsis overflow-hidden">
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
      className={`w-2 h-2 ml-2 rounded-full ${status ? 'bg-[color:var(--online)]' : 'bg-[color:var(--offline)]'}`}
    />
  )
}

FriendsList.UserItem = UserItem
FriendsList.Status = Status

export default FriendsList
