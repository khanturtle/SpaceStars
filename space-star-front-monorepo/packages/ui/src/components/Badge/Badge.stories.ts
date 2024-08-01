import type { Meta, StoryObj } from '@storybook/react'

import Badge from './Badge'

const meta: Meta<typeof Badge> = {
  title: 'TeamCard/Badge',
  component: Badge,
  parameters: {
    layout: 'centered',
  },
  tags: ['autodocs'],
  argTypes: {},
  args: {},
}

export default meta

type Story = StoryObj<typeof Badge>

export const DefaultBadge: Story = {
  args: {
    value: 'LEAGUE OF LEGENDS',
  },
}
export const SmallBadge: Story = {
  args: {
    value: 'LEAGUE OF LEGENDS',
    size: 'small',
  },
}
export const OpenBadge: Story = {
  args: {
    value: '모집중 3/5',
    type: 'open',
  },
}
export const ClosedBadge: Story = {
  args: {
    value: '모집완료 5/5',
    type: 'closed',
    size: 'small',
  },
}
