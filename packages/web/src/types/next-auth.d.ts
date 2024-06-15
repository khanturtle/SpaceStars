import { DefaultSession, DefaultUser, DefaultProfile } from 'next-auth'

declare module 'next-auth' {
  interface Session {
    user?: {
      picture?: string | null
      data: {
        [key: string]: any
      }
    } & DefaultSession['user']
  }

  interface User extends DefaultUser {
    data: {
      [key: string]: any
    }
  }
  // Profile 타입 확장
  interface Profile extends DefaultProfile {
    kakao_account?: {
      email?: string
      profile?: {
        nickname?: string
        profile_image_url?: string
        [key: string]: unknown
      }
      [key: string]: unknown
    }
  }
}
