const FRIEND_BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/friend`
const MATCHING_BASE_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/swipe`

// 친구요청 수락
export interface AlarmRequestType{
    friendUuid: string
}
export async function requestAlarmApprove(token: string, bodyData: AlarmRequestType) {
    try{
        console.log('Sending friend request approve');
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

        console.log("친구요청 수락성공")
    }catch(e){
        console.error(e)
    }
}


// 친구요청 거절
export async function requestAlarmReject(token: string, bodyData: AlarmRequestType) {
    try{
        console.log('Sending friend request reject');
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

// 매칭요청 수락
export async function requestMatchingApprove(token: string, fromMemberUuid: string){

    try{
        console.log('Sending matching request approve');
        const response = await fetch(`${MATCHING_BASE_URL}/agree?fromMemberUuid=${fromMemberUuid}`, {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json',
                Authorization: token ? token : '',
            }
        })
        console.log("매칭요청 수락성공")
        if(!response.ok){
            throw new Error('Failed to request matching approve');
        }
    }catch(e){
        console.error(e)
    }
}

// 매칭요청 거절
export async function requestMatchingReject(token: string, fromMemberUuid: string){

    try{
        console.log('Sending matching request reject');
        const response = await fetch(`${MATCHING_BASE_URL}/reject?fromMemberUuid=${fromMemberUuid}`, {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json',
                Authorization: token ? token : '',
            }
        })

        if(!response.ok){
            throw new Error('Failed to request matching approve');
        }
    }catch(e){
        console.error(e)
    }
}