import type { Meta, StoryObj } from '@storybook/react'

import Calendar from './Calendar'

const meta: Meta<typeof Calendar> = {
  title: 'Icons',
  component: Calendar,
  parameters: {
    layout: 'centered',
  },
}

export default meta

type Story = StoryObj<typeof Calendar>

export const CalendarIcon: Story = { args: {} }
