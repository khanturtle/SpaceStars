declare module 'next-auth' {
  interface Session {
    user?: {
      kakaoProfile?: {
        email: string
        nickname: string
        profileImage: string
      }
      apiResult?: unknown
    }
  }

  interface User extends DefaultUser {
    user?: {
      kakaoProfile?: {
        email: string
        nickname: string
        profileImage: string
      }
      apiResult?: unknown
    }
  }
}

// declare module 'next-auth/jwt' {
//   interface JWT {
//     user: {
//       apiResult?: unknown
//       [key: string]: unknown
//     }
//   }
// }
