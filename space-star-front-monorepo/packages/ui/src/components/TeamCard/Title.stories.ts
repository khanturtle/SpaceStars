import type { Meta, StoryObj } from '@storybook/react'

import Title from './Title'

const meta: Meta<typeof Title> = {
  title: 'TeamCard/Title',
  component: Title,
  parameters: {
    layout: 'centered',
  },
  tags: ['autodocs'],
  argTypes: {},
  args: {},
}

export default meta

type Story = StoryObj<typeof Title>

export const DefaultTitle: Story = {
  args: {
    className: 'w-[364px]',
    title: 'title',
    description: 'description',
    imageUrl: 'https://via.placeholder.com/52x52',
  },
}
export const MTitle: Story = {
  args: {
    className: 'w-[364px]',
    title: '같이 겜 하실 분 구함',
    description: '티어 골드이상만. 챌린저 쌉가능? 야스오 하지마라.',
  },
}
export const STitle: Story = {
  args: {
    className: 'w-[297px]',
    title: '같이 겜 하실 분 구함',
    description: '티어 골드이상만. 챌린저 쌉가능? 야스오 하지마라.',
    descSize: 's',
  },
}
