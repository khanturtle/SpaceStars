import { XCircle } from 'lucide-react';
import { requestAlarmReject } from '@/apis/requestAlarm'

interface RejectButtonProps {
    onClick: () => void;
    token: string
    friendUuid: string
}

const RejectButton = ({ onClick, token, friendUuid }: RejectButtonProps) => {
    const handleReject = async () => {
        try{
            await requestAlarmReject(token, {friendUuid})
            onClick()
        }catch(error){
            console.error('친구요청 거절 실패: ', error)
        }
    }
    
    return (
        <button
            className="bg-pink-400 bg-opacity-60 rounded-full p-2 flex items-center justify-center hover:bg-pink-500 transition duration-300 ease-in-out"
            onClick={onClick}
        >
            <XCircle className="text-white h-5 w-5" />
        </button>
    );
};

export default RejectButton;
