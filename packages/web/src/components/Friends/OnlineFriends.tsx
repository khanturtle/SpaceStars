'use client'

import Image from 'next/image'

import { TmpFriendType } from '@/apis/getFriends'
import useDrag from '@/hooks/useDrag'

import styles from './friends.module.css'

export default function OnlineFriends({
  friends,
}: {
  friends: TmpFriendType[]
}) {
  const {
    isDragging,
    handleMouseDown,
    handleMouseUp,
    handleMouseMove,
    divRef,
  } = useDrag()

  function handleClick(item: TmpFriendType) {
    if (isDragging) {
      return
    }
    console.log(item.index)
  }

  return (
    <section className="mb-9">
      <h3 className="text-[#161616] text-lg not-italic font-medium leading-[normal] mb-6">
        Online Friends
      </h3>

      <div
        className={styles.ul}
        ref={divRef}
        onMouseDown={handleMouseDown}
        onMouseMove={handleMouseMove}
        onMouseUp={handleMouseUp}
      >
        {friends.map((item) => (
          <button
            type="button"
            onClick={() => handleClick(item)}
            key={item.index}
            className="flex flex-col items-center gap-2"
          >
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
          </button>
        ))}
      </div>
    </section>
  )
}
