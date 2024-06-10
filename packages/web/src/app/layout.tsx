import type { Metadata } from 'next'

import '@packages/ui/index.css'

import '@/styles/globals.css'
import '@/styles/fonts.css'
import '@/styles/colors.css'

import AuthSession from '@/components/providers/session-provider'

export const metadata: Metadata = {
  title: 'Dreaming-Stars',
  description: '꿈꾸는 별들',
}

export default function RootLayout({
  children,
  modal,
}: Readonly<{
  children: React.ReactNode
  modal: React.ReactNode
}>) {
  return (
    <html lang="ko">
      <body>
        <AuthSession>
          {children}
          {modal}
        </AuthSession>
      </body>
    </html>
  )
}
