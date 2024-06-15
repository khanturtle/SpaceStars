import type { Metadata } from 'next'

import '@packages/ui/index.css'

import '@/styles/globals.css'
import '@/styles/fonts.css'
import '@/styles/colors.css'
import { Avatar } from '@packages/ui'
import { getServerSession } from 'next-auth/next'

import { options } from '@/app/api/auth/[...nextauth]/options'
import Navbar from '@/components/Navbar/Navbar'
import { ModalProvider } from '@/components/providers/modal-provider'
import AuthSession from '@/components/providers/session-provider'
import WebSocketProvider from '@/components/providers/socket-provider'
import Gutter from '@/components/Gutter'

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
  // TODO: 프로필 이미지 수정
  const profileImage =
    session?.user?.picture ||
    'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAoAAAAKCAYAAACNMs+9AAAAFklEQVR42mN8//HLfwYiAOOoQvoqBABbWyZJf74GZgAAAABJRU5ErkJggg=='

  return (
    <html lang="ko">
      <body>
        <AuthSession>
          <WebSocketProvider>
            <ModalProvider>
              <header>
                <Navbar>
                  <Gutter className="flex-1" />

                  <Navbar.RightBox>
                    {session?.user ? (
                      <Avatar image_url={profileImage} />
                    ) : (
                      <Navbar.LoginButton />
                    )}
                  </Navbar.RightBox>
                </Navbar>
              </header>

              {children}
              {modal}
            </ModalProvider>
          </WebSocketProvider>
        </AuthSession>
      </body>
    </html>
  )
}
