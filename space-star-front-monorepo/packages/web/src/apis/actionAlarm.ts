const SSE_DISCONNECT = `${process.env.NEXT_PUBLIC_API_URL_V1}/sse`

export async function closeSseConnection(uuid: string) {
  try {
    const response = await fetch(SSE_DISCONNECT, {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json',
        UUID: uuid,
      },
    })

    if (!response.ok) {
      console.error(`Response not OK. Status: ${response.status}`)
      throw new Error(
        `Failed to close connection with status ${response.status}`,
      )
    }

    const data = await response.json()
    // console.log("API Response Data: ", data);
    return data.message
  } catch (error) {
    console.error('Error closing connection:', error)
    return
  }
}

const BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/alarm`

export async function modifyAlamrState(
  token?: string,
  alarmId?: string,
  checkStatus?: string,
) {
  try {
    const response = await fetch(`${BASE_URL}/modify/check-status`, {
      method: 'PATCH',
      headers: {
        'Content-Type': 'application/json',
        Authorization: token ? token : '',
      },
      body: JSON.stringify({
        alarmId: alarmId ? alarmId : '',
        checkStatus: checkStatus ? checkStatus : '',
      }),
    })

    if (!response.ok) {
      throw new Error('Failed to modifyAlamrState')
    }

    return await response.json()
  } catch (error) {
    console.error(error)
    return
  }
}
