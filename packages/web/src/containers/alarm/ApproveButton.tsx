import { CheckCircle } from 'lucide-react'
import { requestAlarmApprove } from '@/apis/requestAlarm'

interface ApproveButtonProps {
    onClick: () => void
    token: string
    friendUuid: string
}

const ApproveButton = ({ onClick, token, friendUuid }: ApproveButtonProps) => {
    const handleApprove =async()=>{
        try{
            await requestAlarmApprove(token, {friendUuid})
            onClick()
        }catch(error){
            console.log('친구 요청 수락 실패: ', error)
        }

    }
    
    return (
        <button
            className="bg-purple-600 bg-opacity-60 rounded-full p-2 flex items-center justify-center hover:bg-purple-700 transition duration-300 ease-in-out"
            onClick={handleApprove}
        >
            <CheckCircle className="text-white h-5 w-5" />
        </button>
    );
};

export default ApproveButton;