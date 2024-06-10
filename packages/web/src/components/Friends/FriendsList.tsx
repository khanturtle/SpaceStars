import Image from 'next/image'

import styles from './friends.module.css'

export default function FriendsList({
  title = 'Friends',
  children,
}: {
  title?: string
  children: React.ReactNode
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

// FIXME: 온라인 친구 받아오기
const tmpImage =
  'https://s3-alpha-sig.figma.com/img/9940/be86/a995f084b85137bcc0c1992f82233743?Expires=1719187200&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=iCRYnSw1pQ4EkjZdA0lD9gM-Cc3KFthEoBwSpE7~Jrjv8eXWalTlqxgc~-sQl9IrOH9w45DAt1rIQo5XRsvz8mrk7NX0lKqEZse9JH8FJp~~3NSbXCejQAkZcIiiaUqKWPpGKvi7C2v1ne0NQrUPZOMBWyE4fryCAQkveIGtab~q1hmKmv4bTwa1Dhps2oStMudd0Ao1AaXmjU6Ln2MtDKFNdNkgTZmKrdTuHndusOqd1UKsNkSW3tQW~2wuYjTlI09RMv8Gxu0793Q5t1nL0LGuIP~23Jn-PmUP8fDRpWzSn8-U90MR~JWxdEm0ObT4UGAkcPE5FqJRVUkyc46Y5w__'

type TmpType = {
  index: number
  name: string
  online: boolean
  image_url: string
}
const tmpFriends: TmpType[] = [
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

const OnlineFriends = () => {
  // TODO: 가로 터치 스크롤
  return (
    <ul className={styles.ul}>
      {tmpFriends.map((item) => (
        <li className="flex flex-col items-center gap-2">
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

const AllFriends = () => {
  return (
    <ul className={styles.ul}>
      {tmpFriends.map((item) => (
        <li>
          <div className="flex items-center gap-2" />
        </li>
      ))}
    </ul>
  )
}

FriendsList.OnlineFriends = OnlineFriends
FriendsList.AllFriends = AllFriends
