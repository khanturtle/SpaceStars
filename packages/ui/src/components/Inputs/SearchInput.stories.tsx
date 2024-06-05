import type { Meta, StoryObj } from '@storybook/react'
import IconInput from './SearchInput'

const meta = {
  title: 'Components/Inputs/IconInput',
  component: IconInput,
  parameters: {
    layout: 'centered',
  },
  tags: ['autodocs'],
  argTypes: {},
  args: {},
} satisfies Meta<typeof IconInput>

export default meta
type Story = StoryObj<typeof meta>

export const Default: Story = {
  args: {},
}
