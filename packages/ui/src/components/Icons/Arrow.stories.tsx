import type { Meta, StoryObj } from '@storybook/react'

import Arrow from './Arrow'

const meta: Meta<typeof Arrow> = {
  title: 'Icons/Arrow',
  component: Arrow,
  parameters: {
    layout: 'centered',
  },
  tags: ['autodocs'],
}

export default meta

type Story = StoryObj<typeof Arrow>

export const DownArrowIcon: Story = { args: { type: 'down' } }
export const UpArrowIcon: Story = { args: { type: 'up' } }
export const LeftArrowIcon: Story = { args: { type: 'left' } }
export const RightArrowIcon: Story = { args: { type: 'right' } }
