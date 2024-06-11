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

// TODO: UI 커스텀
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
            'h-[60px] w-full justify-between text-left font-normal',
            !date && 'text-muted-foreground',
          )}
        >
          {date ? format(date, 'yyyy-MM-dd') : <span>날짜 선택</span>}

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
