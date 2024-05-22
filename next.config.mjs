import fs from 'fs';
import path from 'path';

/** @type {import('next').NextConfig} */
const nextConfig = {
  async rewrites() {
    return [
      {
        source: '/signaling/:path*',
        destination: 'http://localhost:8080/signaling/:path*',  // 경로 매핑
      },
    ];
  },
};

export default nextConfig;
