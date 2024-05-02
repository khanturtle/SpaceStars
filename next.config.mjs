/** @type {import('next').NextConfig} */
const nextConfig = {
  images: {
    // (option) serverless dockerlizing을 위해
    unoptimized: true,
  },
  output: 'standalone', // dockerlizing을 위해
}

export default nextConfig
