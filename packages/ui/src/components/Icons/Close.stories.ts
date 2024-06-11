import { Meta, StoryObj } from '@storybook/react'

import Close from './Close'

const meta = {
  title: 'Icons',
  component: Close,
  parameters: {
    layout: 'centered',
  },
  argTypes: {},
  args: {},
} satisfies Meta<typeof Close>

export default meta
type Story = StoryObj<typeof Close>

export const CloseIcon: Story = {
  args: {},
}
