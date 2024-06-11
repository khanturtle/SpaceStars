import { Meta, StoryObj } from '@storybook/react'

import Star from './Star'

const meta = {
  title: 'Icons',
  component: Star,
  parameters: {
    layout: 'centered',
  },
  argTypes: {
    fill: { control: 'color' },
  },
  args: {},
} satisfies Meta<typeof Star>

export default meta
type Story = StoryObj<typeof Star>

export const StarIcon: Story = {
  args: {},
}
