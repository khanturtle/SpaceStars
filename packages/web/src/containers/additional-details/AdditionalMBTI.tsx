'use client'

import { Dispatch, SetStateAction, useEffect, useState } from 'react'

import { getMbtiList, MbtiType } from '@/apis/data'

import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from '@/components/ui/select'

export default function AdditionalMBTI({
  mbtiId,
  setMbtiId,
}: {
  mbtiId: number | undefined
  setMbtiId: Dispatch<SetStateAction<number | undefined>>
}) {
  const [mbtiOptions, setMbtiOptions] = useState<MbtiType[]>([])

  const handleValueChange = (value: string) => {
    const mbtiIdToNumber = Number(value)
    setMbtiId(mbtiIdToNumber)
  }

  // TODO: 테스트
  useEffect(() => {
    const fetchData = async () => {
      const data = await getMbtiList()
      setMbtiOptions(data)
    }

    fetchData()
  }, [])

  return (
    <Select
      value={mbtiId ? mbtiId.toString() : undefined}
      onValueChange={handleValueChange}
    >
      <SelectTrigger
        className="w-full h-[60px] mb-10 border border-[color:var(--White-50,#fff)] shadow-[0px_4px_10px_0px_rgba(37,73,150,0.1)] rounded-[10px] border-solid text-[color:var(--secondary-text-color,#666)] text-base not-italic font-normal leading-[170%] "
        style={{ background: 'rgba(255, 255, 255, 0.5)' }}
      >
        <SelectValue placeholder="MBTI" />
      </SelectTrigger>

      <SelectContent className="relative z-[9999] w-full h-[230px] bg-[white] border-none ">
        {mbtiOptions &&
          mbtiOptions.map((item) => (
            <SelectItem
              key={item.id}
              value={item.id.toString()}
              className="text-base not-italic font-normal leading-[170%] bg-[none] m-0"
            >
              {item.value}
            </SelectItem>
          ))}
      </SelectContent>
    </Select>
  )
}
