import { MetadataRoute } from 'next'

export default function sitemap(): MetadataRoute.Sitemap {
  return [
    {
      url: 'https://spacestars.kr',
      lastModified: new Date(),
      changeFrequency: 'always',
      priority: 1,
    },
  ]
}
