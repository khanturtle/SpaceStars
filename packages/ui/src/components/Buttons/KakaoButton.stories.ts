import type { Meta, StoryObj } from '@storybook/react'
import { fn } from '@storybook/test'
import KakaoButton from './KakaoButton'

const meta = {
  title: 'Components/Buttons/KakaoButton',
  component: KakaoButton,
  parameters: {
    layout: 'centered',
  },
  tags: ['autodocs'],
  argTypes: {},
  args: { onClick: fn() },
} satisfies Meta<typeof KakaoButton>

export default meta
type Story = StoryObj<typeof meta>

export const SignIn: Story = {
  args: {
    label: '카카오 로그인',
  },
}
export const SignUp: Story = {
  args: {
    label: '카카오 회원가입',
  },
}
