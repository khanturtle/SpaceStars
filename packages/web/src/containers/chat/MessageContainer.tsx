import FriendsWrapper from '@/components/Friends/FriendsWrapper'
import { friendsWithBasicDataType } from '@/lib/getFriendsData'

import MessageItem from './MessageItem'
import SearchBox from './SearchBox'

import styles from './chat.module.css'
import MessageWrapper from './MessageWrapper'

export default function MessageContainer({
  friendsList,
  oneToOneChatRooms,
}: {
  friendsList: friendsWithBasicDataType[]
  oneToOneChatRooms: any[]
}) {
  return (
    <div className={styles['messages-container']}>
      <SearchBox />
      <FriendsWrapper friendsList={friendsList} />

      <MessageWrapper chatRoomList={oneToOneChatRooms} />
    </div>
  )
}

MessageContainer.Item = MessageItem
