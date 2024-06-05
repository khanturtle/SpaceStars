import { Meta, StoryObj } from '@storybook/react'

import Logo from './Logo'

const meta = {
  title: 'Icons',
  component: Logo,
  parameters: {
    layout: 'centered',
  },
  argTypes: {},
  args: {},
} satisfies Meta<typeof Logo>

export default meta
type Story = StoryObj<typeof Logo>

export const LogoIcon: Story = {
  args: {},
}
