import { Meta, StoryObj } from '@storybook/react'

import ViewCard from './ViewCard'

const meta = {
  title: 'Icons',
  component: ViewCard,
  parameters: {
    layout: 'centered',
  },
  argTypes: {
    fill: { control: 'color' },
  },
  args: {},
} satisfies Meta<typeof ViewCard>

export default meta
type Story = StoryObj<typeof ViewCard>

export const ViewCardIcon: Story = {
  args: {},
}
