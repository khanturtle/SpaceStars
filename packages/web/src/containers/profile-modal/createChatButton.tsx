'use client'

import { useRouter } from 'next/navigation'

import { Button } from '@packages/ui'

import { createOnetoOneChat } from '@/apis/createOnetoOneChat'

export default function CreateChatButton({ uuid }: { uuid: string }) {
  const router = useRouter()

  const createChat = async () => {
    const chatroomNumber = await createOnetoOneChat(uuid)
    const roomNumber = chatroomNumber.result.roomNumber
    // TODO: 채팅방 ID를 받아 채팅방으로 이동
    router.replace(`/dashboard/chat/${roomNumber}`)
  }

  // TODO: 디자인 수정
  return <Button label="채팅" onClick={createChat} />
}
