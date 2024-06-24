// 친구 관련 api
import {
  sendFriendRequest,
  rejectFriendRequest,
  acceptFriendRequest,
  deleteFriendRequest
} from '@/apis/updateFriends'

import {
  getFriendsDataList,
  getFriendsRequestDataList,
  getFriendsSendDataList,
} from '@/lib/getFriendsData'

export default async function page() {
  // 친구 목록
  const friendsData = await getFriendsDataList()
  // 친구 보낸 요청 목록
  const friendsSendData = await getFriendsSendDataList()
  // 친구 받은 요청 목록
  const friendsRequestData = await getFriendsRequestDataList()

  return (
    <div>
      <div>
        친구: {JSON.stringify(friendsData)}
        친구 삭제 버튼
      </div>
      <div>
        내가보낸
        {JSON.stringify(friendsSendData)}
        요청 취소 버튼
      </div>
      <div>
        내가받은
        {JSON.stringify(friendsRequestData)}
        요청 취소 버튼 + 수락 버튼
      </div>
    </div>
  )
}
