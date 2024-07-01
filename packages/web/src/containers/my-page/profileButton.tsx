'use client'

import { useRef, useState } from 'react'

import { createProfileImage } from '@/apis/createProfileImage'
import { uploadToS3 } from '@/lib/uploadFromUrl'

import { defaultImage } from '@/store/defaultState'

export default function ProfileButton({ image }: { image: string }) {
  const [newImage, setNewImage] = useState(image)
  const fileInputRef = useRef<HTMLInputElement>(null)

  const handleClick = () => {
    fileInputRef.current?.click()
  }

  const handleFileChange = async (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files && e.target.files[0]) {
      const file = e.target.files[0]
      const imageUrl = await handleUpload(file)
      if (imageUrl) {
        await updateProfileImage(imageUrl)
        setNewImage(imageUrl)
      }
    }
  }

  const handleUpload = async (file: File) => {
    const s3ImageUrl = await uploadToS3(file, file.name)
    console.log('S3 Image URL:', s3ImageUrl)
    return s3ImageUrl
  }

  const updateProfileImage = async (imageUrl: string) => {
    const req = {
      profileImageUrl: imageUrl,
      main: true,
    }
    await createProfileImage(req)
  }

  return (
    <>
      <button onClick={handleClick}>
        <img
          src={newImage ?? defaultImage}
          alt="Profile"
          className="object-cover w-20 h-20 border-2 border-white rounded-full"
        />
      </button>
      <input
        type="file"
        ref={fileInputRef}
        onChange={handleFileChange}
        style={{ display: 'none' }}
        accept="image/*"
      />
    </>
  )
}
