import {
  DeleteObjectCommand,
  PutObjectCommand,
  S3Client,
} from '@aws-sdk/client-s3'
import AWS from 'aws-sdk'

// export async function getS3

export async function uploadToS3(file: File, fileName: string) {
  const s3 = new S3Client({
    region: process.env.NEXT_PUBLIC_AWS_REGION as string,
    credentials: {
      accessKeyId: process.env.NEXT_PUBLIC_AWS_ACCESS_KEY_ID as string,
      secretAccessKey: process.env.NEXT_PUBLIC_AWS_SECRET_ACCESS_KEY as string,
    },
  })

  // AWS.config.update({
  //   region: process.env.AWS_REGION,
  //   accessKeyId: process.env.AWS_ACCESS_KEY_ID,
  //   secretAccessKey: process.env.AWS_SECRET_ACCESS_KEY,
  // })

  const params = {
    Bucket: process.env.NEXT_PUBLIC_AWS_BUCKET_NAME || '',
    Key: process.env.NEXT_PUBLIC_AWS_BUCKET_NAME + '/' + fileName,
    Body: file,
  }

  try {
    const data = await s3.send(new PutObjectCommand(params))
    // const data = await new AWS.S3.ManagedUpload({
    //   params: params, // Fixed the params assignment
    // }).promise()

    console.log(data)
    return data
    // state에 저장
  } catch (error) {
    console.error('Error uploading to S3:', error)
  }
}
