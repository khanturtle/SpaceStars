import React, { useContext, forwardRef, useImperativeHandle } from 'react';
import { ModalContext } from '@/components/providers/modal-provider';
import FormLayout from '@/components/form/formLayout';
import AlarmListItem from './AlarmListItem';
import { useAlarmData } from './useAlarmData';

interface AlarmListContainerProps {
    accessToken: string;
    refreshKey: number;
}

const AlarmListContainer = forwardRef<any, AlarmListContainerProps>(({ accessToken, refreshKey }, ref) => {
    const { openModal } = useContext(ModalContext);
    const alarms = useAlarmData(accessToken, refreshKey);

    useImperativeHandle(ref, () => ({
        openAlarmModal: () => {
            openModal(
                <div className="relative h-full flex flex-col items-center">
                    <FormLayout className="relative h-full px-[204px] pt-[90px] pb-[85px] flex flex-col items-center">
                        <FormLayout.Legend title="알림" />
                        <div className="flex flex-col items-center custom-scrollbar" style={{ maxHeight: '60vh', width: '100%', overflowY: 'scroll', scrollbarWidth: 'none', msOverflowStyle: 'none' }}>
                            {alarms.length === 0 ? (
                                <p>새로운 알림이 없습니다</p>
                            ) : (
                                alarms.map((alarm) => (
                                    <AlarmListItem 
                                        key={alarm.index} 
                                        senderProfileImage={alarm.senderProfileImage} 
                                        senderNickname={alarm.senderNickname} 
                                        alarmType={alarm.alarmType === 'FRIEND' ? '친구요청' : '매칭요청'} 
                                        content={alarm.content} 
                                        token={accessToken}
                                        senderUuid={alarm.senderUuid}
                                        checkStatus={alarm.checkStatus}
                                        alarmId={alarm.alarmId}
                                    />
                                ))
                            )}
                        </div>
                    </FormLayout>
                </div>
            );
        }
    }));

    return null;
});

export default AlarmListContainer;