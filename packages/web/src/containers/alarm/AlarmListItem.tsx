import ApproveButton from './ApproveButton';
import RejectButton from './RejectButton';

interface AlarmListItemProps {
  senderProfileImage: string;
  senderNickname: string;
  alarmType: string;
  content: string;
  token: string;
  senderUuid: string;
  checkStatus: string;
  alarmId: string
}

const AlarmListItem = ({
  senderProfileImage,
  senderNickname,
  alarmType,
  content,
  token,
  senderUuid,
  checkStatus,
  alarmId
}: AlarmListItemProps) => {
  const isUnread = checkStatus === 'UNREAD';

  const handleAction = () => {
    console.log('Action completed');
    // 추가적인 액션을 수행할 수 있습니다.
  };

  return (
    <div
      className={`mb-2 p-4 rounded-lg shadow-md flex items-center justify-between hover:bg-opacity-80 transition duration-300 ease-in-out ${
        isUnread ? 'bg-white bg-opacity-30' : 'bg-gray-300'
      }`}
      style={{ width: '550px', maxWidth: '600px' }}
    >
      <div className="flex items-center">
        <img src={senderProfileImage} alt="Profile" className="w-12 h-12 rounded-full mr-4" />
        <div className={isUnread ? '' : 'text-gray-500'}>
          <p className="font-semibold">
            {senderNickname}님이 <span className={isUnread ? 'text-purple-500' : 'text-gray-500'}> {alarmType}</span>을 보냈습니다.
          </p>
          {alarmType !== '친구요청' && <p>{content}</p>}
        </div>
      </div>
      <div className="flex space-x-2">
        <ApproveButton 
          onClick={handleAction} 
          token={token} 
          friendUuid={senderUuid} 
          checkStatus={checkStatus} 
          alarmId={alarmId}
          alarmType={alarmType}
        />
        <RejectButton 
          onClick={handleAction} 
          token={token} 
          friendUuid={senderUuid} 
          checkStatus={checkStatus} 
          alarmId={alarmId}
          alarmType={alarmType}
        />
      </div>
    </div>
  );
};

export default AlarmListItem;
