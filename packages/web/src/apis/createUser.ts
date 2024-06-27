'use server'

const AUTH_BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/auth`

export async function createUser(
  imageUrl: string,
  isAvailable: boolean,
  prevState: unknown,
  formData: FormData,
) {
  // 1. 닉네임 중복검사 여부:
  if (!isAvailable) {
    return {
      status: 400,
      message: '입력한 닉네임이 중복되지 않는지 확인해 주세요.',
    }
  }
  const postFormData = {
    email: formData.get('email'),
    nickname: formData.get('nickname'),
    infoAgree: formData.get('infoAgree') === 'on',
    birth: formData.get('birth'),
    gender: formData.get('gender'),
  }

  try {
    const res = await fetch(`${AUTH_BASE_URL}/join`, {
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
    console.error('Join Error:', error)
  }

  return {
    status: 400,
    message: '',
  }
}
