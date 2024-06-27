import { ApiResponseType, AlarmListType } from '@/types/type';

const ALARM_LIST_URL = `${process.env.NEXT_PUBLIC_API_URL_V1}/alarm/list`

export async function getAlarms(
    token?: string,
):Promise<(ApiResponseType & {result: AlarmListType[]}) | undefined>{

    try{
        const response = await fetch(ALARM_LIST_URL, {
            headers: {
                'Content-Type': 'application/json',
                Authorization: token ? token : '',
            },
        });

        if(!response.ok){
            throw new Error('Failed to get alarm list');
        }

        const data = await response.json();
        return data.result.alarmList;
    }catch(error){
        console.error(error);
        return
    }
}