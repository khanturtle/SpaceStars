import { useState, useEffect } from 'react';
import { getAlarms, getNicknameByUuid, getProfileImageByUuid } from '@/apis/getAlarms';
import { AlarmListType } from '@/types/type';


export function useAlarmData(accessToken: string, refreshKey: number) {
    const [alarms, setAlarms] = useState<AlarmListType[]>([])

    useEffect(() => {
        async function fetchAlarms() {
            const alarmData = await getAlarms(accessToken);
            if (alarmData) {
                const alarmsWithDetails = await Promise.all(alarmData.map(async (alarm) => {
                    const nicknameData = await getNicknameByUuid(alarm.senderUuid);
                    const profileImageData = await getProfileImageByUuid(alarm.senderUuid, accessToken);
                    return {
                        ...alarm,
                        senderNickname: nicknameData?.nickname || 'Unknown',
                        senderProfileImage: profileImageData?.profileImageUrl || '/images/default-image.jpg',
                    };
                }));
                setAlarms(alarmsWithDetails);
            }
        };
        fetchAlarms();
    }, [accessToken, refreshKey]);

    return alarms;
}
