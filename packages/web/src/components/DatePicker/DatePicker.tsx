'use client'

import * as React from 'react'

import { CalendarIcon } from '@packages/ui'
import { format } from 'date-fns'
import { ko } from 'date-fns/locale'

import { Button } from '@/components/ui/button'
import { Calendar } from '@/components/ui/calendar'
import {
  Popover,
  PopoverContent,
  PopoverTrigger,
} from '@/components/ui/popover'
import { cn } from '@/lib/utils'

interface DatePickerCustomProps {
  id: string
  date?: Date
  setDate: React.Dispatch<React.SetStateAction<Date | undefined>>
}

export default function CustomDatePicker({
  id,
  date,
  setDate,
}: DatePickerCustomProps) {
  return (
    <Popover>
      <PopoverTrigger asChild>
        <Button
          variant="outline"
          className={cn(
            'h-[60px] w-full justify-between text-left font-normal border border-[color:var(--White-50,#fff)] border-solid rounded-[10px]',
            !date && 'text-muted-foreground',
          )}
          style={{ background: 'rgba(255, 255, 255, 0.5)' }}
        >
          <div className="flex flex-col items-start justify-center w-full">
            <legend className="text-[color:var(--secondary-text-color,#666)] text-xs not-italic font-normal leading-[170%]">
              생년월일
            </legend>
            <div className="text-[color:var(--main-color,#111)] text-base not-italic font-normal leading-[170%] w-full border-[none]">
              {date ? format(date, 'yyyy-MM-dd') : <span>날짜 선택</span>}
            </div>
          </div>

          <div>
            <CalendarIcon />
          </div>
        </Button>
      </PopoverTrigger>

      <PopoverContent className="z-[5000] w-auto p-0">
        <Calendar
          id={id}
          mode="single"
          selected={date}
          onSelect={setDate}
          initialFocus
          required
          locale={ko}
        />
      </PopoverContent>
    </Popover>
  )
}
