/** @type {import('next').NextConfig} */

const nextConfig = {
  siteUrl: 'https://spacestars.kr',
  generateRobotsTxt: true,
  images: {
    domains: [
      't1.kakaocdn.net',
      'space-star-bucket.s3.ap-northeast-2.amazonaws.com',
      's3-alpha-sig.figma.com',
    ],
  },
  experimental: {
    externalDir: true,
    workerThreads: false,
    cpus: 1,
  },
  eslint: {
    // Warning: This allows production builds to successfully complete even if
    // your project has ESLint errors.
    ignoreDuringBuilds: true,
  },
  experimental: {
    missingSuspenseWithCSRBailout: false,
  },
}

export default nextConfig
