import { CheckCircle } from 'lucide-react';
import { requestAlarmApprove } from '@/apis/requestAlarm';
import { modifyAlamrState } from '@/apis/actionAlarm'

interface ApproveButtonProps {
    onClick: () => void;
    token: string;
    friendUuid: string;
    checkStatus: string;
    alarmId: string
}

const ApproveButton = ({ onClick, token, friendUuid, checkStatus, alarmId }: ApproveButtonProps) => {
    const handleApprove = async () => {
        try {
            await requestAlarmApprove(token, { friendUuid });
            await modifyAlamrState(token, alarmId, "APPROVE")
            onClick();
        } catch (error) {
            console.log('친구 요청 수락 실패: ', error);
        }
    };

    const isUnread = checkStatus === 'UNREAD';

    return (
        <button
            className={`rounded-full p-2 flex items-center justify-center transition duration-300 ease-in-out ${
                isUnread
                    ? 'bg-purple-600 bg-opacity-60 hover:bg-purple-700'
                    : 'bg-gray-400'
            }`}
            onClick={isUnread ? handleApprove : undefined}
        >
            <CheckCircle className={`h-5 w-5 ${isUnread ? 'text-white' : 'text-gray-500'}`} />
        </button>
    );
};

export default ApproveButton;
