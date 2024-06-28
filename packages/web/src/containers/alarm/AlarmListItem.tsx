import ApproveButton from './ApproveButton';
import RejectButton from './RejectButton';

interface AlarmListItemProps {
    senderProfileImage: string
    senderNickname: string
    alarmType: string
    content: string
    token: string
    senderUuid: string
}

const AlarmListItem = ({ senderProfileImage, senderNickname, alarmType, content, token, senderUuid }: AlarmListItemProps) => {

    const handleAction = () => {
        console.log('Action completed');
        // 추가적인 액션을 수행할 수 있습니다.
    };

    return (
        <div className="mb-2 p-4 bg-white bg-opacity-30 rounded-lg shadow-md flex items-center justify-between hover:bg-opacity-80 transition duration-300 ease-in-out" style={{ width: '550px', maxWidth: '600px' }}>
            <div className="flex items-center">
                <img src={senderProfileImage} alt="Profile" className="w-12 h-12 rounded-full mr-4" />
                <div>
                    <p className="font-semibold">{senderNickname}님이 <span className="text-purple-500"> {alarmType}</span>을 보냈습니다.</p>
                    {alarmType !== '친구요청' && <p>{content}</p>}
                </div>
            </div>
            <div className="flex space-x-2">
                <ApproveButton onClick={handleAction} token={token} friendUuid={senderUuid}/>
                <RejectButton onClick={handleAction} token={token} friendUuid={senderUuid}/>
            </div>
        </div>
    );
};

export default AlarmListItem;
