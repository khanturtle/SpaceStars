import { Meta, StoryObj } from '@storybook/react'

import ViewList from './ViewList'

const meta = {
  title: 'Icons',
  component: ViewList,
  parameters: {
    layout: 'centered',
  },
  argTypes: {
    fill: { control: 'color' },
  },
  args: {},
} satisfies Meta<typeof ViewList>

export default meta
type Story = StoryObj<typeof ViewList>

export const ViewListIcon: Story = {
  args: {},
}
