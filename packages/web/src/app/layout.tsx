import type { Metadata, Viewport } from 'next'

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
  title: '우주별: 우리 주변의 별별 사람들',
  description:
    '다양한 게임을 즐기며 새로운 친구를 만날 수 있는 커뮤니티 플랫폼',
  keywords: ['게임', '우주', '플랫폼', '엔터테인먼트', '콘텐츠'],
  metadataBase: new URL('https://spacestars.kr'),
  openGraph: {
    type: 'website',
    url: 'https://spacestars.kr',
    title: '우주별: 우리 주변의 별별 사람들',
    description:
      '다양한 게임을 즐기며 새로운 친구를 만날 수 있는 커뮤니티 플랫폼',
    images: [
      { url: '/images/opengraph-image.png', alt: '우주별 서비스 이미지' },
    ],
  },
}

export const viewport: Viewport = {
  themeColor: 'light',
  width: 'device-width',
  initialScale: 1,
  maximumScale: 2,
  userScalable: false,
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
