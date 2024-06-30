const FRIEND_BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/friend`
// const MACHING_BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/friend`

// 친구요청 수락
export interface AlarmRequestType{
    friendUuid: string
}
export async function requestAlarmApprove(token: string, bodyData: AlarmRequestType) {
    try{
        const response = await fetch(`${FRIEND_BASE_URL}/request`, {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json',
                Authorization: token ? token : '',
            },
            body: JSON.stringify(bodyData),
        })

        if(!response.ok){
            throw new Error('Failed to request alarm approve');
        }
    }catch(e){
        console.error(e)
    }
}


// 친구요청 거절
export async function requestAlarmReject(token: string, bodyData: AlarmRequestType) {
    try{
        const response = await fetch(`${FRIEND_BASE_URL}/request`, {
            method: `DELETE`,
            headers:{
                'Content-Type': 'application/json',
                Authorization: token ? token : '',
            },
            body: JSON.stringify(bodyData),
        })

        if(!response.ok){
            throw new Error('Failed to request alarm reject');
        }
    }catch(e){
        console.error(e)
    }
} 