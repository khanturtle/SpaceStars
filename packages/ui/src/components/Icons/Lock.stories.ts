import { Meta, StoryObj } from '@storybook/react'

import Lock from './Lock'

const meta = {
  title: 'Icons',
  component: Lock,
  parameters: {
    layout: 'centered',
  },
  argTypes: {
    fill: { control: 'color' },
  },
  args: {},
} satisfies Meta<typeof Lock>

export default meta
type Story = StoryObj<typeof Lock>

export const LockIcon: Story = {
  args: {},
}
