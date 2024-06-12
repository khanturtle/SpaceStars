import type { Metadata } from 'next'

import AuthSession from '@/components/providers/session-provider'

import '@/styles/globals.css'

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
    <html lang="en">
      <body>
        <AuthSession>
          {children}
          {modal}
        </AuthSession>
      </body>
    </html>
  )
}
