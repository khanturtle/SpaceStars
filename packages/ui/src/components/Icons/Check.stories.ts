import { Meta, StoryObj } from '@storybook/react'

import Check from './Check'

const meta = {
  title: 'Icons',
  component: Check,
  parameters: {
    layout: 'centered',
  },
  argTypes: {},
  args: {},
} satisfies Meta<typeof Check>

export default meta
type Story = StoryObj<typeof Check>

export const CheckIcon: Story = {
  args: {},
}
