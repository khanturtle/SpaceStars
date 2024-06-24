import FriendsWrapper from '@/components/Friends/FriendsWrapper'
import { friendsWithBasicDataType } from '@/lib/getFriendsData'

import MessageItem from './MessageItem'
import SearchBox from './SearchBox'

import styles from './chat.module.css'

const MessageWrapper = ({
  title,
  chatRoomList,
  token,
}: {
  title: string
  chatRoomList: any[]
  token: string
}) => {
  return (
    <section className="mb-9">
      <h3 className="text-[#161616] text-lg not-italic font-medium leading-[normal] mb-6">
        {title}
      </h3>

      <div className="flex flex-col gap-2 scroll-none">
        {chatRoomList.map((chatRoom) => (
          <MessageItem key={chatRoom.index} room={chatRoom} token={token} />
        ))}
      </div>
    </section>
  )
}

export default function MessageContainer({
  friendsList,
  oneToOneChatRooms,
  token,
}: {
  friendsList: friendsWithBasicDataType[]
  oneToOneChatRooms: any[]
  token: string
}) {
  return (
    <div className={styles['messages-container']}>
      <SearchBox />
      <FriendsWrapper friendsList={friendsList} />
      <MessageWrapper
        title="Messages"
        chatRoomList={oneToOneChatRooms}
        token={token}
      />
      {/* TODO: 그룹 채팅방 */}
      {/* <MessageWrapper title="Group Messages" chatRoomList={oneToOneChatRooms} token={token} /> */}
    </div>
  )
}
