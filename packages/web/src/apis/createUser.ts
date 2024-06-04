'use server'

async function createUser(
  imageUrl: string,
  prevState: unknown,
  formData: FormData,
) {
  console.log(imageUrl)
  console.log(formData)

  const postFormData = {
    email: formData.get('email'),
    nickname: formData.get('nickname'),
    infoAgree: formData.get('infoAgree') === 'true',
    birth: formData.get('birth'),
    gender: formData.get('gender'),
    imageUrl,
  }
  try {
    const res = await fetch(`${process.env.NEXT_PUBLIC_API_URL_V1}/auth/join`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(postFormData),
    }).then((r) => r.json())

    return {
      status: res.code as number,
      message: res.message as string,
    }
  } catch (error) {
    console.error('Error:', error)
  }

  return {
    status: 400,
    message: '',
  }
}

export default createUser
