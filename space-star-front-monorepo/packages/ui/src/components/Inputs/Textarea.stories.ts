import type { Meta, StoryObj } from '@storybook/react'
import Textarea from './Textarea'

const meta: Meta<typeof Textarea> = {
  title: 'Components/Inputs/Textarea',
  component: Textarea,
  parameters: {
    layout: 'centered',
  },
  tags: ['autodocs'],
  argTypes: {},
  args: {},
}

export default meta

type Story = StoryObj<typeof Textarea>

export const Default: Story = { args: {} }
