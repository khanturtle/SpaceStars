import { DefaultSession } from 'next-auth'

declare module 'next-auth' {
  interface Session {
    user?: {
      kakaoProfile?: {
        email: string
        nickname: string
        profileImage: string
      }
      apiToken?: unknown
    } & DefaultSession['user']
  }

  interface User extends DefaultUser {
    user?: {
      kakaoProfile?: {
        email: string
        nickname: string
        profileImage: string
      }
      apiToken?: unknown
    }
  }

  // Profile 타입 확장
  interface Profile {
    kakao_account?: {
      [key: string]: unknown
    } & DefaultSession['profile']
  }
}
