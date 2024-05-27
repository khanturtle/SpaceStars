import type { Meta, StoryObj } from '@storybook/react'
import Checkbox from './Checkbox'

const meta = {
  title: 'Components/Inputs/Checkbox',
  component: Checkbox,
  parameters: {
    layout: 'centered',
  },
  tags: ['autodocs'],
  argTypes: {
    isChecked: { control: 'boolean' },
    disabled: { control: 'boolean' },
    text: { control: 'text' },
  },
  args: {},
} satisfies Meta<typeof Checkbox>

export default meta
type Story = StoryObj<typeof meta>

export const DefaultCheckBox: Story = {
  args: {
    id: 'checkbox',
    text: 'label',
    isChecked: false,
  },
}
export const SignUpCheckBox: Story = {
  args: {
    id: 'agree',
    text: '개인정보 수집동의',
    isChecked: true,
  },
}
