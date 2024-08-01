import type { Meta, StoryObj } from '@storybook/react'
import Input from './Input'

const meta = {
  title: 'Components/Inputs/Input',
  component: Input,
  parameters: {
    layout: 'centered',
  },
  tags: ['autodocs'],
  argTypes: {},
  args: {},
} satisfies Meta<typeof Input>

export default meta
type Story = StoryObj<typeof meta>

export const DefaultInput: Story = {
  args: {
    id: 'input',
    label: 'label',
    placeholder: 'placeholder',
  },
}
export const LabelInput: Story = {
  args: {
    id: 'nickname',
    label: '닉네임',
    required: true,
  },
}
export const DisabledInput: Story = {
  args: {
    id: 'nickname',
    label: '닉네임',
    disabled: true,
    value: 'email@email',
  },
}
