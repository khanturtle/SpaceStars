import type { Meta, StoryObj } from '@storybook/react'
import { fn } from '@storybook/test'
import Select from './Select'

const meta = {
  title: 'Components/Inputs/Select',
  component: Select,
  parameters: {
    layout: 'centered',
  },
  tags: ['autodocs'],
  argTypes: {},
  args: { onChange: fn() },
} satisfies Meta<typeof Select>

export default meta
type Story = StoryObj<typeof meta>

export const DefaultSelect: Story = {
  args: {
    id: 'id',
    className: 'w-[300px]',
    options: [{ value: '', label: 'Select' }],
  },
}
export const GenderSelect: Story = {
  args: {
    id: 'gender',
    label: '성별',
    required: true,
    options: [
      { value: 'male', label: '남자' },
      { value: 'female', label: '여자' },
    ],
  },
}
export const MBTISelect: Story = {
  args: {
    id: 'mbti',
    options: [
      { value: 'INTP', label: 'INTP' },
      { value: 'ENTP', label: 'ENTP' },
    ],
  },
}
