import { Meta, StoryObj } from '@storybook/react'

import Search from './Search'

const meta = {
  title: 'Icons',
  component: Search,
  parameters: {
    layout: 'centered',
  },
  argTypes: {
    fill: { control: 'color' },
  },
  args: {},
} satisfies Meta<typeof Search>

export default meta
type Story = StoryObj<typeof Search>

export const SearchIcon: Story = {
  args: {},
}
