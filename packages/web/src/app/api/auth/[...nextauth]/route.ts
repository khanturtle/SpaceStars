import NextAuth from 'next-auth'
import KakaoProvider from 'next-auth/providers/kakao'

const handler = NextAuth({
  providers: [
    KakaoProvider({
      clientId: process.env.KAKAO_CLIENT_ID as string,
      clientSecret: process.env.KAKAO_CLIENT_SECRET as string,
    }),
  ],
  callbacks: {
    // async signIn({ user, profile }) {
    //   // 회원이 아니면, 회원가입으로 이동
    //   if (!profile) {
    //     return '/additional-info'
    //   }
    //   return true
    // },
    // async redirect(url, baseUrl) {
    //   return baseUrl
    // },
    async signIn({ user, account, profile }) {
      // 로그인 성공 시 세션 커스터마이징
      return true
    },
    // async redirect({ url, baseUrl }) {
    //   console.log('Redirect callback called:', { url, baseUrl })
    //   if (typeof window !== 'undefined' && window.opener) {
    //     console.log('Posting message to opener')
    //     window.opener.postMessage({ type: 'kakao-login', url }, baseUrl)
    //     window.close()
    //     return baseUrl
    //   }
    //   return url
    // },
    // async session({ session, user }) {
    //   // 세션 정보 커스터마이징
    //   return session
    // },
  },
  pages: {
    // signIn: '/sign-in',
  },
})

export { handler as GET, handler as POST }
