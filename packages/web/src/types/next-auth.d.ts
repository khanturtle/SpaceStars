declare module 'next-auth' {
  interface Session {
    user: {
      kakaoProfile?: {
        email?: string
        nickname?: string
        profileImage?: string
      } & DefaultSession['user']
    }
  }

  interface JWT {
    kakaoProfile?: {
      email?: string
      nickname?: string
      profileImage?: string
    }
  }
}
