/* eslint-disable no-param-reassign */
import { getMainProfileImg } from '@/apis/profileImage'
import { NextAuthOptions } from 'next-auth'
import KakaoProvider from 'next-auth/providers/kakao'

export const options: NextAuthOptions = {
  providers: [
    KakaoProvider({
      clientId: process.env.KAKAO_CLIENT_ID as string,
      clientSecret: process.env.KAKAO_CLIENT_SECRET as string,
    }),
  ],
  callbacks: {
    async signIn({ profile, user }) {
      const res = await fetch(
        `${process.env.NEXT_PUBLIC_API_URL_V1}/auth/login`,
        {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            email: profile?.kakao_account?.email,
          }),
        },
      ).then((r) => r.json())

      // 회원이면, 회원 정보 저장 및 로그인
      if (res.code === 200) {
        // 회원 정보 저장
        user.data = res.result

        // TODO: 대표 프로필 사진 가져오기
        getMainProfileImg()

        return true
      }
      /** 회원이 아니면, 회원가입 페이지로 이동
       *  2005: 비회원
       *  2006: 탈퇴한 회원 */
      if (res.code === 2005 || res.code === 2006) {
        const kakaoProfile = {
          email: profile?.kakao_account?.email as string,
          nickname: profile?.kakao_account?.profile?.nickname as string,
          profileImage: profile?.kakao_account?.profile
            ?.profile_image_url as string,
        }

        // throw new Error('UserSignUpRequired')
        const queryParams = new URLSearchParams(kakaoProfile)
        return `/additional-info?${queryParams}`
      }

      throw new Error('API Request Failed')
    },
    // TODO: 백엔드 엑세스토큰이랑 만료시간 맞춰서 로그아웃 또는 세션 연장 처리
    async jwt({ token, user, trigger, session }) {
      if (user) {
        token.data = {
          ...user.data,
        }
      }
      if (trigger === 'update') {
        token.data = { ...(token.data || {}), ...session.data }
      }
      return token
    },
    async session({ session, token }) {
      session.user = {
        picture: token.picture,
        data: token.data || {},
        ...session.user,
      }

      return session
    },
  },
  pages: {
    signIn: '/sign-in',
    error: '/auth/error',
  },
}
