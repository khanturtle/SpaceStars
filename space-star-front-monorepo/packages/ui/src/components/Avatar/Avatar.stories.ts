import type { Meta, StoryObj } from '@storybook/react'

import Avatar from './Avatar'

const meta: Meta<typeof Avatar> = {
  title: 'Avatar',
  component: Avatar,
  parameters: {
    layout: 'centered',
  },
  tags: ['autodocs'],
}

export default meta

type Story = StoryObj<typeof Avatar>

export const DefaultAvatar: Story = {
  args: {
    image_url: 'https://via.placeholder.com/50x46',
  },
}

export const NoImagetAvatar: Story = {
  args: {
    image_url: '',
  },
}
