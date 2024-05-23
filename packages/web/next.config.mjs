/** @type {import('next').NextConfig} */

import path from 'path'

const nextConfig = {
  experimental: {
    externalDir: true,
  },
  // webpack: (config) => {
  //   config.resolve.alias['@'] = path.resolve('./src')
  //   return config
  // },
}

export default nextConfig
