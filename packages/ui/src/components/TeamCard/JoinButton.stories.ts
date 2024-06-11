import type { Meta, StoryObj } from '@storybook/react'
import { fn } from '@storybook/test'

import JoinButton from './JoinButton'

const meta: Meta<typeof JoinButton> = {
  title: 'TeamCard/JoinButton',
  component: JoinButton,
  parameters: {
    layout: 'centered',
  },
  tags: ['autodocs'],
  argTypes: {},
  args: {
    onClick: fn(),
  },
}

export default meta

type Story = StoryObj<typeof JoinButton>

export const Default: Story = { args: {} }

export const FinButton: Story = {
  args: {
    isFinished: true,
  },
}
export const LockButton: Story = {
  args: {
    isLocked: true,
    isFinished: true,
  },
}
