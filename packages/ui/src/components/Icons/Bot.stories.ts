import { Meta, StoryObj } from '@storybook/react'

import Bot from './Bot'

const meta = {
  title: 'Icons',
  component: Bot,
  parameters: {
    layout: 'centered',
  },
  argTypes: {},
  args: {},
} satisfies Meta<typeof Bot>

export default meta
type Story = StoryObj<typeof Bot>

export const BotIcon: Story = {
  args: {},
}
