import { Meta, StoryObj } from '@storybook/react'

import Theme from './Theme'

const meta = {
  title: 'Icons',
  component: Theme,
  parameters: {
    layout: 'centered',
  },
  argTypes: {},
  args: {},
} satisfies Meta<typeof Theme>

export default meta
type Story = StoryObj<typeof Theme>

export const ThemeIcon: Story = {
  args: {},
}
