import { dataType } from '@/app/dashboard/friends-list/page'
import React from 'react'
import MenuButton from './button/MenuButton'
import MessageButton from './button/MessageButton'

function FriendListTable({ data }: { data: dataType[] }) {
  return (
    <>
      {data &&
        data.map((item) => (
          <div
            key={item.index}
            className="w-[90vw] max-w-[98%] h-[30vh] max-h-[72px] px-4 py-3.5 bg-white rounded-[15px] m-2 flex items-center"
          >
            <img
              className="w-[40px] h-[40px] rounded-full"
              src={item.profileImageUrl}
              alt="profile"
            />
            <div className="ml-3 text-[14px] font-bold text-[#333333]">
              {item.nickname}
            </div>
            <div className="ml-auto flex items-center">
              <MessageButton />
              <MenuButton />
            </div>
          </div>
        ))}
    </>
  )
}

export default FriendListTable
