package com.spacestar.back.chat.vo.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoomResVo {
    private int index;
    private String roomNumber;
    private String otherMemberUuid;
}
