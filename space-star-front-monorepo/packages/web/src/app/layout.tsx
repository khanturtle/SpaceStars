import type { Metadata, Viewport } from 'next'
import { getServerSession } from 'next-auth'

import { options } from './api/auth/[...nextauth]/options'

import '@packages/ui/index.css'

import '@/styles/globals.css'
import '@/styles/fonts.css'
import '@/styles/colors.css'
import '@/styles/loader.css'

import { getMainProfileImage } from '@/apis/getProfileImage'

import Navbar from '@/components/Navbar/Navbar'
import { ModalProvider } from '@/components/providers/modal-provider'
import AuthSession from '@/components/providers/session-provider'
import WebSocketProvider from '@/components/providers/socket-provider'

import { defaultImage } from '@/store/defaultState'
import { ToastProvider } from '@/components/Toast/toast-provider'

export const metadata: Metadata = {
  title: '우주별 - 우리 주변의 별별 사람들',
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
  themeColor: 'dark',
  width: 'device-width',
}

export default async function RootLayout({
  children,
  modal,
}: Readonly<{
  children: React.ReactNode
  modal: React.ReactNode
}>) {
  const session = await getServerSession(options)

  // 세션이 있으면, 대표 프로필
  const profileMainImageData = session ? await getMainProfileImage() : undefined
  const profileImageUrl = profileMainImageData
    ? profileMainImageData.result.profileImageUrl
    : defaultImage

  return (
    <html lang="ko">
      <head>
        <meta
          name="google-site-verification"
          content="RF3jYM4llYmMJX3IkZbGaclTJ74I2LihgTOlqjsqppg"
        />
      </head>
      <body data-theme="dark" className="bg-[color:var(--background-color)]">
        <AuthSession>
          <ToastProvider>
            <WebSocketProvider>
              <ModalProvider>
                <Navbar session={session} profileImageUrl={profileImageUrl} />
                {children}
                {modal}
              </ModalProvider>
            </WebSocketProvider>
          </ToastProvider>
        </AuthSession>
      </body>
    </html>
  )
}
