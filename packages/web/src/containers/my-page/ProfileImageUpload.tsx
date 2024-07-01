'use client'

import { createProfileImage } from '@/apis/createProfileImage'
import { uploadToS3 } from '@/lib/uploadFromUrl'
import { useState } from 'react'

export default function ProfileImageUpload() {
  const [selectedFile, setSelectedFile] = useState<File | null>(null)

  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files) {
      setSelectedFile(e.target.files[0])
    }
  }

  const handleUpload = async () => {
    // console.log(file)
    if (selectedFile) {
      const s3ImageUrl = await uploadToS3(selectedFile, selectedFile.name)
      console.log('S3 Image URL:', s3ImageUrl)
      return s3ImageUrl
    }
  }

  const updateProfileImage = async (imageUrl: string) => {
    // Call API to update profile image
    const req = {
      profileImageUrl: imageUrl,
      main: false,
    }
    createProfileImage(req)
  }

  const handleClick = async () => {
    const imageUrl = await handleUpload()
    if (imageUrl) {
      await updateProfileImage(imageUrl)
    }
  }

  return (
    <div>
      <input type="file" onChange={handleFileChange} />
      <button onClick={handleClick}>프로필 추가</button>
    </div>
  )
}
