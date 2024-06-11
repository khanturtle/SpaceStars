import type { Metadata } from 'next'

import '@packages/ui/index.css'

import '@/styles/globals.css'
import '@/styles/fonts.css'
import '@/styles/colors.css'
import { Avatar } from '@packages/ui'
import { getServerSession } from 'next-auth/next'

import { options } from '@/app/api/auth/[...nextauth]/options'
import Navbar from '@/components/Navbar/Navbar'
import AuthSession from '@/components/providers/session-provider'

export const metadata: Metadata = {
  title: 'Dreaming-Stars',
  description: '꿈꾸는 별들',
}

export default async function RootLayout({
  children,
  modal,
}: Readonly<{
  children: React.ReactNode
  modal: React.ReactNode
}>) {
  const session = await getServerSession(options)
  const profileImage =
    session?.user?.picture ||
    'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAoAAAAKCAYAAACNMs+9AAAAFklEQVR42mN8//HLfwYiAOOoQvoqBABbWyZJf74GZgAAAABJRU5ErkJggg=='

  return (
    <html lang="ko">
      <body>
        <AuthSession>
          <Navbar>
            <Navbar.MidBox className="flex-1">
              <Navbar.MidItems />
            </Navbar.MidBox>

            <Navbar.RightBox>
              {session?.user?.data ? (
                <Avatar image_url={profileImage} />
              ) : (
                <Navbar.LoginButton />
              )}
            </Navbar.RightBox>
          </Navbar>

          {children}
          {modal}
        </AuthSession>
      </body>
    </html>
  )
}
