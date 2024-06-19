// FIXME: s3 업로드 서버로 수정 필요

import { NextApiRequest, NextApiResponse } from 'next'

export default async function handler(
  req: NextApiRequest,
  res: NextApiResponse,
) {
  try {
    const {
      AWS_REGION,
      AWS_BUCKET_NAME,
      AWS_ACCESS_KEY_ID,
      AWS_SECRET_ACCESS_KEY,
    } = process.env

    if (
      !AWS_REGION ||
      !AWS_BUCKET_NAME ||
      !AWS_ACCESS_KEY_ID ||
      !AWS_SECRET_ACCESS_KEY
    ) {
      return res.status(400).json({ error: 'Missing environment variables' })
    }

    const s3Response = {
      region: AWS_REGION,
      bucketName: AWS_BUCKET_NAME,
      accessKeyId: AWS_ACCESS_KEY_ID,
      secretAccessKey: AWS_SECRET_ACCESS_KEY,
    }

    return res.status(200).json(s3Response)
  } catch (error) {
    console.error(error)
    return res.status(500).json({ error: 'Internal server error' })
  }
}
