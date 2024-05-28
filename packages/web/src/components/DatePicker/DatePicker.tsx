'use client'

import * as React from 'react'

import { CalendarIcon } from '@packages/ui'
import { format } from 'date-fns'

import { Button } from '@/components/ui/button'
import { Calendar } from '@/components/ui/calendar'
import {
  Popover,
  PopoverContent,
  PopoverTrigger,
} from '@/components/ui/popover'
import { cn } from '@/lib/utils'

interface DatePickerCustomProps {
  className?: string
  id: string
}

export default function DatePickerCustom({
  // eslint-disable-next-line @typescript-eslint/no-unused-vars
  className,
  id,
}: DatePickerCustomProps) {
  const [date, setDate] = React.useState<Date>()

  return (
    <Popover>
      <PopoverTrigger asChild>
        <Button
          variant="outline"
          className={cn(
            'w-[280px] justify-start text-left font-normal',
            !date && 'text-muted-foreground',
          )}
        >
          {date ? format(date, 'PPP') : <span>Pick a date</span>}
          <div>
            <CalendarIcon />
          </div>
        </Button>
      </PopoverTrigger>

      <PopoverContent className="w-auto p-0">
        <Calendar
          id={id}
          mode="single"
          selected={date}
          onSelect={setDate}
          initialFocus
          required
        />
      </PopoverContent>
    </Popover>
  )
}
