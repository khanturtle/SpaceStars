import { XCircle } from 'lucide-react';
import { requestAlarmReject, requestMatchingReject} from '@/apis/requestAlarm';
import { modifyAlamrState } from '@/apis/actionAlarm'

interface RejectButtonProps {
    onClick: () => void;
    token: string;
    friendUuid: string;
    checkStatus: string;
    alarmId: string
    alarmType: string
}

const RejectButton = ({ onClick, token, friendUuid, checkStatus, alarmId, alarmType }: RejectButtonProps) => {
    const handleReject = async () => {
        try {
            console.log(alarmType)
            if (alarmType === '친구요청') {
                await requestAlarmReject(token, { friendUuid });
            } else if (alarmType === '매칭요청') {
                await requestMatchingReject(token, friendUuid);
            }
            await modifyAlamrState(token, alarmId, "REJECT");
            onClick();
        } catch (error) {
            console.log('요청 처리 실패: ', error);
        }
    };
    

    const isUnread = checkStatus === 'UNREAD';

    return (
        <button
            className={`rounded-full p-2 flex items-center justify-center transition duration-300 ease-in-out ${
                isUnread
                    ? 'bg-pink-600 bg-opacity-60 hover:bg-pink-700'
                    : 'bg-gray-400'
            }`}
            onClick={isUnread ? handleReject : undefined}
        >
            <XCircle className={`h-5 w-5 ${isUnread ? 'text-white' : 'text-gray-500'}`} />
        </button>
    );
};

export default RejectButton;
