/** @type {import('next').NextConfig} */

const nextConfig = {
  experimental: {
    externalDir: true,
    workerThreads: false,
    cpus: 1,
  },
}

export default nextConfig
