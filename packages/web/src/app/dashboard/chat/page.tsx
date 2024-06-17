import './tmp.css'

import { TmpMsgList } from '@/apis/chat'
import { TmpFriendType } from '@/apis/friends'
import OnlineFriends from '@/components/Friends/OnlineFriends'
import ChatRoomContainer from '@/containers/chat/ChatRoomContainer'
import MessageContainer from '@/containers/chat/MessageContainer'
import SearchBox from '@/containers/chat/SearchBox'

// FIXME: 온라인 친구 받아오기
const tmpImage =
  'https://s3-alpha-sig.figma.com/img/9940/be86/a995f084b85137bcc0c1992f82233743?Expires=1719187200&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=iCRYnSw1pQ4EkjZdA0lD9gM-Cc3KFthEoBwSpE7~Jrjv8eXWalTlqxgc~-sQl9IrOH9w45DAt1rIQo5XRsvz8mrk7NX0lKqEZse9JH8FJp~~3NSbXCejQAkZcIiiaUqKWPpGKvi7C2v1ne0NQrUPZOMBWyE4fryCAQkveIGtab~q1hmKmv4bTwa1Dhps2oStMudd0Ao1AaXmjU6Ln2MtDKFNdNkgTZmKrdTuHndusOqd1UKsNkSW3tQW~2wuYjTlI09RMv8Gxu0793Q5t1nL0LGuIP~23Jn-PmUP8fDRpWzSn8-U90MR~JWxdEm0ObT4UGAkcPE5FqJRVUkyc46Y5w__'

const tmpFriends: TmpFriendType[] = [
  {
    index: 1,
    name: 'John Doe',
    online: true,
    image_url: tmpImage,
  },
  {
    index: 2,
    name: 'Bob Smith',
    online: false,
    image_url: tmpImage,
  },
  {
    index: 3,
    name: 'ㅇㅅㅇ',
    online: true,
    image_url: tmpImage,
  },
  {
    index: 4,
    name: 'John Doe',
    online: true,
    image_url: tmpImage,
  },
  {
    index: 5,
    name: 'Bob Smith',
    online: false,
    image_url: tmpImage,
  },
  {
    index: 6,
    name: 'ㅇㅅㅇ',
    online: true,
    image_url: tmpImage,
  },
]

// FIXME: 메시지 리스트 받아오기
const tmpMsgList: TmpMsgList[] = [
  {
    index: 1,
    avatar: 'https://picsum.photos/200/300',
    name: 'John Doe',
    message: 'Hello, how are you?',
    time: '10:45 AM',
    unreadMessages: 1,
  },
  {
    index: 2,
    avatar: 'https://picsum.photos/200/301',
    name: 'Jane Doe',
    message: 'I am good, thanks. How about you?',
    time: '10:46 AM',
    unreadMessages: 2,
  },
  {
    index: 3,
    avatar: 'https://picsum.photos/200/302',
    name: 'John Doe',
    message: 'I am good too. Thanks for asking.',
    time: '10:47 AM',
    unreadMessages: 3,
  },
  {
    index: 11,
    avatar: 'https://picsum.photos/200/300',
    name: 'John Doe',
    message: 'Hello, how are you?',
    time: '10:45 AM',
    unreadMessages: 0,
  },
  {
    index: 21,
    avatar: 'https://picsum.photos/200/301',
    name: 'Jane Doe',
    message: 'I am good, thanks. How about you?',
    time: '10:46 AM',
    unreadMessages: 0,
  },
  {
    index: 31,
    avatar: 'https://picsum.photos/200/302',
    name: 'John Doe',
    message: 'I am good too. Thanks for asking.',
    time: '10:47 AM',
    unreadMessages: 4,
  },
  {
    index: 12,
    avatar: 'https://picsum.photos/200/300',
    name: 'John Doe',
    message: 'Hello, how are you?',
    time: '10:45 AM',
    unreadMessages: 0,
  },
  {
    index: 22,
    avatar: 'https://picsum.photos/200/301',
    name: 'Jane Doe',
    message: 'I am good, thanks. How about you?',
    time: '10:46 AM',
    unreadMessages: 0,
  },
  {
    index: 32,
    avatar: 'https://picsum.photos/200/302',
    name: 'John Doe',
    message: 'I am good too. Thanks for asking.',
    time: '10:47 AM',
    unreadMessages: 0,
  },
]

export default function page() {
  return (
    <section className="relative flex flex-row w-full h-full">
      <div className="left">
        <SearchBox />

        <OnlineFriends friends={tmpFriends} />

        <MessageContainer>
          <MessageContainer.MessageList messages={tmpMsgList} />
        </MessageContainer>
      </div>

      <ChatRoomContainer />
    </section>
  )
}
