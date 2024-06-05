import type { Metadata } from 'next'

import Navbar from '@/components/Navbar'
import AuthSession from '@/components/providers/session-provider'

import '@/styles/globals.css'

import '@packages/ui/index.css'

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
          <Navbar />

          {children}
          {modal}
        </AuthSession>
      </body>
    </html>
  )
}
