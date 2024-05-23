import type { Meta, StoryObj } from '@storybook/react'
import { fn } from '@storybook/test'
import Button from './Button'

const meta = {
  title: 'Components/Buttons/Button',
  component: Button,
  parameters: {
    layout: 'centered',
  },
  tags: ['autodocs'],
  argTypes: {
    backgroundColor: { control: 'color' },
  },
  args: { onClick: fn() },
} satisfies Meta<typeof Button>

export default meta
type Story = StoryObj<typeof meta>

export const Primary: Story = {
  args: {
    primary: true,
    label: 'Button',
  },
}
export const Secondary: Story = {
  args: {
    primary: false,
    label: 'Button',
  },
}

export const Rounded: Story = {
  args: {
    primary: true,
    shape: 'rounded',
    label: 'Button',
    className: 'h-[53px]',
  },
}

export const RoundedShadow: Story = {
  args: {
    primary: true,
    shape: 'rounded',
    label: 'Button',
    className: 'h-[53px]',
    shadow: true,
  },
}

export const Oval: Story = {
  args: {
    primary: true,
    shape: 'oval',
    size: 'large',
    label: 'Button',
    className: 'h-[46px]',
    backgroundColor: '#000',
    fontColor: 'white',
  },
}
