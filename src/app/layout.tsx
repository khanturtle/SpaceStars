import type { Metadata } from 'next'
import '@/styles/globals.css'
import Navbar from '@/components/Navbar'
import AuthSession from '@/components/providers/session-provider'

export const metadata: Metadata = {
  title: 'Dreaming-Stars',
  description: '꿈꾸는 별들',
}

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode
}>) {
  return (
    <html lang="en">
      <body>
        <AuthSession>
          <Navbar />
          {children}
        </AuthSession>
      </body>
    </html>
  )
}
