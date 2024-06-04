export async function fetchWithToken(
  url: string,
  options: RequestInit = {},
  accessToken: string,
) {
  const headers = new Headers(options.headers || {})

  // 헤더에 Authorization 추가
  headers.append('Authorization', accessToken)

  const updatedOptions: RequestInit = {
    ...options,
    headers,
  }

  const response = await fetch(url, updatedOptions)

  // 에러 핸들링
  if (!response.ok) {
    const error = await response.json()
    throw new Error(error.message || 'Something went wrong')
  }

  return response.json()
}
