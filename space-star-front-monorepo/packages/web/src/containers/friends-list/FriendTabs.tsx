'use client'

import { useSearchParams, useRouter } from 'next/navigation'
import React, { useEffect, useState } from 'react'

const TabButton = ({
  id,
  tabName,
  title,
  type,
}: {
  id: string
  tabName: string
  title: string
  type?: string
}) => {
  const router = useRouter()

  return (
    <li className="mr-10" role="presentation">
      <p
        className={`inline-block hover:text-purple-600 hover:border-gray-300 rounded-t-lg py-4 px-4 text-sm font-medium text-center border-transparent border-b-2 dark:text-gray-400 dark:hover:text-gray-300 ${
          type === tabName
            ? 'font-semibold text-[color:var(--sidebar-text-active)] border-b-2 border-purple-600'
            : 'text-[color:var(--text-desc)] '
        }`}
        onClick={() => router.push(`/dashboard/friends-list?type=${tabName}`)}
      >
        {title}
      </p>
    </li>
  )
}

const FriendTabs = ({ type }: { type: string }) => {
  const tabs = [
    {
      id: 0,
      tabName: 'all',
      title: '친구목록',
    },
    {
      id: 1,
      tabName: 'sender',
      title: '보낸 요청 목록',
    },
    {
      id: 2,
      tabName: 'receiver',
      title: '받은 요청 목록',
    },
  ]

  return (
    <div className="max-w-full mx-auto">
      <div className="border-b border-purple-600 dark:border-gray-700 mb-4">
        <ul
          className="flex flex-wrap -mb-px"
          id="myTab"
          data-tabs-toggle="#myTabContent"
          role="tablist"
        >
          {tabs.map((tab) => (
            <TabButton
              id="friends"
              tabName={tab.tabName}
              title={tab.title}
              type={type}
            />
          ))}
        </ul>
      </div>
    </div>
  )
}

export default FriendTabs
