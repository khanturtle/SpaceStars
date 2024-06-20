import FriendsWrapper from '@/components/Friends/FriendsWrapper'
import { friendsWithBasicDataType } from '@/lib/getFriendsData'

import MessageItem from './MessageItem'
import SearchBox from './SearchBox'

import styles from './chat.module.css'

export default function MessageContainer({
  friendsList,
}: {
  friendsList: friendsWithBasicDataType[]
}) {
  return (
    <div className={styles['messages-container']}>
      <SearchBox />
      <FriendsWrapper friendsList={friendsList} />

      {/* <MessageWrapper /> */}
      {/* <section className="mb-9">
        <h3 className="text-[#161616] text-lg not-italic font-medium leading-[normal] mb-6">
          Messages
        </h3>

        <div className="flex flex-col gap-2 scroll-none">{children}</div>
      </section> */}
    </div>
  )
}

MessageContainer.Item = MessageItem
