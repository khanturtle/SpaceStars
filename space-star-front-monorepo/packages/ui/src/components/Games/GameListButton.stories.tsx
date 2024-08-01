import type { Meta, StoryObj } from '@storybook/react'

import GameListButton from './GameListButton'

const meta: Meta<typeof GameListButton> = {
  title: 'Components/Games/GameListButton',
  component: GameListButton,
  parameters: {
    layout: 'centered',
  },
  tags: ['autodocs'],
  argTypes: { onClick: { action: 'clicked' } },
  args: {},
}

export default meta

type Story = StoryObj<typeof GameListButton>

export const DefaultGameListButton: Story = {
  args: {
    item: {
      gameImage:
        'https://s3-alpha-sig.figma.com/img/1d3e/76db/bf5d355441d7035183675e2b076a8840?Expires=1718582400&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=Fe6jr1yIJxbZ9KiReWFCvIUoOMWMLPSgR0iiRlswZHLMmhp3pVvdNpd10y5GT0euPGki03NiwTUt~7Cm4i3n4G2NXcI5rRiSYqvqcKXTJQLdeij~taiC5B5t6evckLEjeoEF0XEQDOziGvZlvKOTljrMSKeO3nYy7QDsTzgtlBSZk4OWItdGGm7hJTdVMhqz96kGLb8xYhjkfvwXgbtNMyarPVcyTlIKylux9iTzB9vCUNGviUCqXVasEpjGnAB~FVIs7Nr193ya8SpwBoxfbI04Jvv9rHlN0astfIb~JgK4mTTVdAss0l4WKIwJAhvBWMJnkFbCM7nSPIYgq~g06A__',
      gameLogo:
        'https://s3-alpha-sig.figma.com/img/434b/06d6/0bfc112079bf2c0b0d74da38e1b5279c?Expires=1718582400&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=lYs-7hAvk5rDeTC1yldjP8Bvx5EnPRjCCxKiska81F3hUcG1K0kULMbhkbmGd6yxhddiX67NS7RrJr4ETLKw2kY5B3fVqBwPsxYC7lFxJlPjp7DPwO0Z94rJJtQN1FCxuHEg17StoLvOJkzT624-u2o3yzSOVGLfbqvDF8iBqr1z4R~4h3czj5nQ8VmAc82yN32QeXkSk~3xM~cFWhb5m~GCSI9KPGgPDjRwHtB7W9ha3agzbnD3huTQNG07V~jdNt0vRXWaGd8NywnCE3eLgicMkYrPM58UX12lfmbKtADKmmPGwnJzn1zJl2JtgzAiqU4iKWhcWn5qLWeu8lvEqg__',
      gameName: '리그오브레전드',
    },
  },
}
export const ClickedGameListButton: Story = {
  args: {
    item: {
      gameImage:
        'https://s3-alpha-sig.figma.com/img/800b/4010/1aaa56251b764aad2a6400ec8343628a?Expires=1718582400&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=qeMZ0V5BybCWfRnIfymwAAMhTc52q0NedgZr4LEg6IfNyB~uRpPIpBN5isuSPwfEk48kJWW4mmODb1tpfvqniT7n7JxZ3ALy2eVXE0D9y01QxWY5D9HLk~Lj~kJ-npwgbdVtkrbp79vOfBStMoIhnecvpLn0T5Jn1qySUt-UJBHwn2DwU3-hrR-5GFkN64wQI5~7k~V5LBMw4HtzpP6iYF0iSXn8MbPWs8W2ljDAxHEO3IFtT5Eovu7L1nmZVKj-siEsNaRlBIy3An8IARGPo7VbRmLn1krf9rkaRPCo6iueb3Sg1GWQLC6qZCa~9fbjYwpYcREEbKvlrIZyFgYsDA__',
      gameLogo:
        'https://s3-alpha-sig.figma.com/img/3409/1994/5c1a9fa9c91e3a0bab6f81fa6e87b6c2?Expires=1718582400&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=DLvMMTrTeSTOC499ueZqWwV2DUzrkX0-FGURhKYml7yb-e8H2Ci25Nk80aeDoUCSF0nQASc2fdex7Ek1adtW-AuXiW-HZpkDXM0m~UG-XOA-lcSlhkM9p4im-k9mlc4zXm8-Ce-suYFW-Veji0g3SVYOEkA5JfHVUfGzXTsRfXjwPubAdxFWldKiV5TpCLy8fNitImFJES0Scv4IUnAWZLUu-VDJBYFgeNRPSzVVWjZb-NOTJi2TaH~SfOwzGD2NHHbfZAM25deQ7TbqWAQKCe8KFt0kKzX9khX9xNrPca~Rt6eIS1Qx55oJB-YdTi9ccPOfE-7c3ezwT3Dz0pBnyw__',
      gameName: '리그오브레전드',
    },
    isClicked: true,
  },
}
