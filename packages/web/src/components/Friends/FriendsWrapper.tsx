'use client'

import { useRouter } from 'next/navigation'
import Image from 'next/image'
import Link from 'next/link'

import { useReducer } from 'react'

import useDrag from '@/hooks/useDrag'
import { friendsWithBasicDataType } from '@/lib/getFriendsData'

import styles from './friends.module.css'

export default function FriendsWrapper({
  friendsList,
}: {
  friendsList: friendsWithBasicDataType[]
}) {
  const [isDrag, setIsDrag] = useReducer((state) => {
    return !state
  }, false)

  const { handleMouseDown, handleMouseUp, handleMouseMove, divRef } = useDrag()

  const handleMouseDownWithDrag = (e: React.MouseEvent<HTMLDivElement>) => {
    handleMouseDown(e)
    setIsDrag()
  }

  const handleMouseUpWithDrag = (e: React.MouseEvent<HTMLDivElement>) => {
    handleMouseUp(e)
    setTimeout(() => {
      setIsDrag()
    }, 1000)
  }

  return (
    <section className="mb-9">
      <div className="flex justify-between">
        <h3 className="text-[color:var(--text-title)] text-lg not-italic font-medium leading-[normal] mb-6">
          Friends
        </h3>
        <Link
          href="/dashboard/friends-list?type=all"
          className="text-xs text-[color:var(--text-title)]"
        >
          more
        </Link>
      </div>

      <div
        className={styles.ul}
        ref={divRef}
        onMouseDown={handleMouseDownWithDrag}
        onMouseMove={handleMouseMove}
        onMouseUp={handleMouseUpWithDrag}
      >
        {friendsList.map((item) => (
          <FriendsItem key={item.friendUuid} item={item} isDrag={isDrag} />
        ))}
      </div>
    </section>
  )
}

export const FriendsItem = ({
  item,
  isDrag,
  children,
}: {
  item: friendsWithBasicDataType
  isDrag: boolean
  children?: React.ReactNode
}) => {
  const router = useRouter()

  const handleClick = () => {
    if (isDrag) return

    router.push(`/profile/${item.friendUuid}`)
  }

  return (
    <button
      type="button"
      onClick={handleClick}
      className="flex flex-col items-center gap-2"
    >
      <div className="relative w-12 h-12">
        <Image
          className="w-12 h-12 rounded-full"
          width={48}
          height={48}
          src={item.profileImageUrl}
          alt={item.nickname}
        />
        {children}
      </div>

      <p className="w-11 text-ellipsis whitespace-nowrap overflow-hidden text-[color:var(--grey-text)] text-center text-xs not-italic font-normal leading-[normal]">
        {item.nickname}
      </p>
    </button>
  )
}

const Status = ({ status }: { status: boolean }) => {
  return (
    <div
      className={`w-3 h-3 shrink-0 border border-[color:var(--White-50,#fff)] absolute rounded-full right-0 bottom-0
      ${status ? 'bg-[color:var(--online)]' : 'bg-[color:var(--offline)]'}
      `}
    />
  )
}

FriendsItem.Status = Status
