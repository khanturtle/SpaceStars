'use client'

import { uploadToS3 } from '@/lib/uploadFromUrl'
import Image from 'next/image'
import { useState } from 'react'

export default function Upload() {
  const imageUrl = '/images/default-image.jpg'

  const getImageFile = async (url: string): Promise<File> => {
    const res = await fetch(url)
    const blob = await res.blob()
    return new File([blob], 'default-image.jpg', { type: 'image/jpeg' })
  }

  const [selectedFile, setSelectedFile] = useState<File | null>(null)

  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files) {
      console.log(e.target.files)
      setSelectedFile(e.target.files[0])
    }
  }

  const handleUpload = async () => {
    const file = await getImageFile(imageUrl)
    // console.log(file)
    if (file) {
      const s3ImageUrl = await uploadToS3(file, file.name)
      console.log('S3 Image URL:', s3ImageUrl)
    }
  }

  return (
    <div>
      <button onClick={handleUpload}>Upload Image from URL</button>

      <input type="file" onChange={handleFileChange} />
      <Image
        src="https://space-star-bucket.s3.ap-northeast-2.amazonaws.com/space-star-bucket/default-image.jpg"
        width={100}
        height={100}
        alt=""
      />
    </div>
  )
}
