import type { Meta, StoryObj } from '@storybook/react'
import GameOption from './GameOption'

const meta: Meta<typeof GameOption> = {
  title: 'Components/Games/GameOption',
  component: GameOption,
  parameters: {
    layout: 'centered',
  },
  tags: ['autodocs'],
  argTypes: {},
  args: {},
}

export default meta

type Story = StoryObj<typeof GameOption>

export const Basic: Story = {
  args: {
    gameImage:
      'https://upload.wikimedia.org/wikipedia/commons/thumb/f/fc/Valorant_logo_-_pink_color_version.svg/544px-Valorant_logo_-_pink_color_version.svg.png?20200516191842',
    gameName: '발로란트',
  },
}
export const Options: Story = {
  args: {
    gameImage:
      'https://upload.wikimedia.org/wikipedia/commons/thumb/f/fc/Valorant_logo_-_pink_color_version.svg/544px-Valorant_logo_-_pink_color_version.svg.png?20200516191842',
    gameName: '발로란트',
    options: [{ index: 1 }],
  },
}
