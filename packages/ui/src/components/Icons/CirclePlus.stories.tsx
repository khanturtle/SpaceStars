import { Meta, StoryObj } from '@storybook/react'

import CirclePlus from './CirclePlus'

const meta = {
  title: 'Icons',
  component: CirclePlus,
  parameters: {
    layout: 'centered',
  },
  argTypes: {},
  args: {},
} satisfies Meta<typeof CirclePlus>

export default meta
type Story = StoryObj<typeof CirclePlus>

export const CirclePlusIcon: Story = {
  args: {},
}
