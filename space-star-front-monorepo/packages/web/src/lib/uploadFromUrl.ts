import {
  // DeleteObjectCommand,
  PutObjectCommand,
  S3Client,
} from '@aws-sdk/client-s3'
// import AWS from 'aws-sdk'

export async function uploadToS3(file: File, fileName: string) {
  const s3 = new S3Client({
    region: process.env.NEXT_PUBLIC_AWS_REGION as string,
    credentials: {
      accessKeyId: process.env.AWS_ACCESS_KEY_ID as string,
      secretAccessKey: process.env.AWS_SECRET_ACCESS_KEY as string,
    },
  })

  // AWS.config.update({
  //   region: process.env.AWS_REGION,
  //   accessKeyId: process.env.AWS_ACCESS_KEY_ID,
  //   secretAccessKey: process.env.AWS_SECRET_ACCESS_KEY,
  // })
  const fileExtension = fileName.split('.').pop() || ''
  const currentTime = new Date().getTime()
  const uniqueFileName = `${fileName.split('.')[0]}_${currentTime}.${fileExtension}`

  const params = {
    Bucket: process.env.NEXT_PUBLIC_AWS_BUCKET_NAME || '',
    Key: `${process.env.NEXT_PUBLIC_AWS_BUCKET_NAME}/${uniqueFileName}`,
    Body: file,
  }

  try {
    await s3.send(new PutObjectCommand(params))

    const S3Url = `https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/${params.Key}`

    return S3Url
  } catch (error) {
    console.error('Error uploading to S3:', error)
  }
}
