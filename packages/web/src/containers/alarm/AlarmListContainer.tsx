'use client';

import { useContext, useEffect, useState } from 'react';
import { ModalContext } from '@/components/providers/modal-provider';
import FormLayout from '@/components/form/formLayout';
import { PlusIcon } from 'lucide-react';
import { getAlarms, getNicknameByUuid, getProfileImageByUuid } from '@/apis/getAlarms';
import { AlarmListType } from '@/types/type';
import AlarmListItem from './AlarmListItem';

interface AlarmListContainerProps {
    accessToken: string;
}

const AlarmListContainer = ({ accessToken }: AlarmListContainerProps) => {
    const { openModal } = useContext(ModalContext);
    const [alarms, setAlarms] = useState<AlarmListType[]>([]);

    useEffect(() => {
        const fetchAlarms = async () => {
            console.log("Using token to fetch alarms: ", accessToken);
            const alarmData = await getAlarms(accessToken);
            console.log("Alarm set: ", alarmData);
            if (alarmData) {
                const alarmsWithDetails = await Promise.all(alarmData.map(async (alarm) => {
                    const nicknameData = await getNicknameByUuid(alarm.senderUuid);
                    const profileImageData = await getProfileImageByUuid(alarm.senderUuid, accessToken);
                    return {
                        ...alarm,
                        senderNickname: nicknameData?.nickname || 'Unknown',
                        senderProfileImage: profileImageData?.profileImageUrl || '/default-profile.png',
                    };
                }));
                setAlarms(alarmsWithDetails);
            } else {
                console.log("No alarms data received or an error occurred");
            }
        };
        fetchAlarms();
    }, [accessToken]);

    const handleClick = () => {
        openModal(
            <div className="relative h-full flex flex-col items-center">
                <FormLayout className="relative h-full px-[100px] pt-[90px] pb-[85px] flex flex-col items-center">
                    <FormLayout.Legend title="알림"/>
                    <div className="flex flex-col items-center custom-scrollbar" style={{ maxHeight: '60vh', width: '100%', overflowY: 'scroll', scrollbarWidth: 'none', msOverflowStyle: 'none' }}>
                        {alarms && alarms.length > 0 ? (
                            alarms.map((alarm) => (
                                <AlarmListItem 
                                    key={alarm.index}
                                    senderProfileImage={alarm.senderProfileImage}
                                    senderNickname={alarm.senderNickname}
                                    alarmType={alarm.alarmType === 'FRIEND' ? '친구요청' : '매칭요청'}
                                    content={alarm.content}
                                    token={accessToken}
                                    senderUuid={alarm.senderUuid}
                                />
                            ))
                        ) : (
                            <p>새로운 알림이 없습니다.</p>
                        )}
                    </div>
                </FormLayout>
            </div>
        );
    };

    return (
        <button type="button" onClick={handleClick}>
            <PlusIcon strokeWidth={2.5} />
        </button>
    );
}

export default AlarmListContainer;
