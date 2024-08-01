import type { Meta, StoryObj } from '@storybook/react'
import GameButton from './GameButton'

const meta = {
  title: 'Components/Games/GameButton',
  component: GameButton,
  parameters: {
    layout: 'centered',
  },
  tags: ['autodocs'],
  argTypes: { onClick: { action: 'clicked' } },
  args: {},
} satisfies Meta<typeof GameButton>

export default meta
type Story = StoryObj<typeof meta>

export const DefaultGameButton: Story = {
  args: {
    item: {
      gameImage:
        'https://upload.wikimedia.org/wikipedia/commons/thumb/f/fc/Valorant_logo_-_pink_color_version.svg/544px-Valorant_logo_-_pink_color_version.svg.png?20200516191842',
      gameName: '발로란트',
    },
  },
}
export const ClickedGameButton: Story = {
  args: {
    item: {
      gameImage:
        'https://upload.wikimedia.org/wikipedia/commons/thumb/f/fc/Valorant_logo_-_pink_color_version.svg/544px-Valorant_logo_-_pink_color_version.svg.png?20200516191842',
      gameName: '발로란트',
    },
    isClicked: true,
  },
}
