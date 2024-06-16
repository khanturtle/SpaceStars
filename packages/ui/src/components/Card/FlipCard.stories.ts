import type { Meta, StoryObj } from '@storybook/react'
import FlipCard from './FlipCard'

const meta = {
  title: 'Components/Cards/FlipCard',
  component: FlipCard,
  parameters: {
    layout: 'centered',
  },
  tags: ['autodocs'],
  argTypes: {},
  args: {},
} satisfies Meta<typeof FlipCard>

export default meta
type Story = StoryObj<typeof meta>

export const Default: Story = {
  args: {
    front: 'front',
    back: 'back',
  },
}
