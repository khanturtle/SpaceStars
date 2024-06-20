'use client'

import { useRouter } from 'next/navigation'

import { Button } from '@packages/ui'

import { createOnetoOneChat } from '@/apis/createOnetoOneChat'

export default function CreateChatButton({ uuid }: { uuid: string }) {
  const router = useRouter()

  const createChat = async () => {
    const chatroomNumber = await createOnetoOneChat(uuid)
    console.log(chatroomNumber)
    // TODO: 채팅방 ID를 받아 채팅방으로 이동
    // 일단 채팅화면으로 이동.
    router.push(`/dashboard/chat`)
  }

  // TODO: 디자인 수정
  return <Button label="채팅" onClick={createChat} />
}
