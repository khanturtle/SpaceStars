/** @type {import('next-sitemap').IConfig} */

module.exports = {
  siteUrl: 'http://localhost:3000',
  generateRobotsTxt: true,
  sitemapSize: 7000,
  changefreq: 'always',
  priority: 1,
  exclude: [
    // '/exclude/review', // 페이지 주소 하나만 제외시키는 경우
    '/chat/**', // 하위 주소 전체를 제외시키는 경우
  ], // sitemap 등록 제외 페이지 주소
  robotsTxtOptions: {
    policies: [
      {
        userAgent: '*', // 모든 agent 허용
        allow: '/', // 모든 페이지 주소 크롤링 허용
        disallow: ['/chat'], // 시작하는 페이지 주소 크롤링 금지
      },
    ],
  }, // robots.txt 옵션 설정
}
