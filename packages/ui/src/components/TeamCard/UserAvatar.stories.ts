import type { Meta, StoryObj } from '@storybook/react'

import UserAvatar from './UserAvatar'

const meta: Meta<typeof UserAvatar> = {
  title: 'TeamCard/UserAvatar',
  component: UserAvatar,
  parameters: {
    layout: 'centered',
  },
  tags: ['autodocs'],
  argTypes: {},
  args: {},
}

export default meta

type Story = StoryObj<typeof UserAvatar>

export const Default: Story = {
  args: {
    users: [
      {
        userId: 1,
        profileImage: 'https://via.placeholder.com/52x52',
      },
    ],
  },
}
export const User2: Story = {
  args: {
    users: [
      {
        userId: 1,
        profileImage: 'https://via.placeholder.com/52x52',
      },
      { userId: 2, profileImage: 'https://via.placeholder.com/1x1' },
    ],
  },
}
export const User3: Story = {
  args: {
    size: 'small',
    users: [
      {
        userId: 1,
        profileImage: 'https://via.placeholder.com/52x52',
      },
      { userId: 2, profileImage: 'https://via.placeholder.com/1x1' },
      { userId: 3, profileImage: 'https://via.placeholder.com/1x1' },
    ],
  },
}
