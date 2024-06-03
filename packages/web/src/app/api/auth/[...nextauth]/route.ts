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
    async signIn({ profile, account }) {
      const res = await fetch(
        `${process.env.NEXT_PUBLIC_API_URL_V1}/auth/login`,
        {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            email: profile?.kakao_account.email,
          }),
        },
      )
      if (!res.ok) {
        throw new Error('API Request Failed')
      }

      const response = await res.json()

      // 회원이 아니면, 회원가입 페이지로 이동
      if (response.code === 2005) {
        const kakaoProfile = {
          email: profile.kakao_account.email,
          nickname: profile.kakao_account.profile.nickname,
          profileImage: profile.kakao_account.profile.profile_image_url,
        }

        account.kakaoProfile = kakaoProfile
        // throw new Error('UserSignUpRequired')

        const queryParams = new URLSearchParams(kakaoProfile)
        return `/additional-info?${queryParams}`
      }

      if (account) {
        account.apiResult = response.result
        // console.log(account)
      }
      // account.apiResult = response.result
      return true
    },
    async jwt({ token, account }) {
      // token.kakaoProfile = account.kakaoProfile
      // console.log('token: ', token, account)
      if (account?.apiResult) {
        token.apiResult = account.apiResult
      }
      // console.log('token', token)
      return token
    },

    async session({ session, token }) {
      if (!session.user.apiResult) {
        session.user = token
      }
      // console.log('session', session)
      // console.log('session22', token)
      return session
    },
  },
  pages: {
    signIn: '/sign-in',
    error: '/auth-error',
  },
})

export { handler as GET, handler as POST }
