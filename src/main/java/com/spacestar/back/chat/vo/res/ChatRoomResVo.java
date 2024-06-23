package com.spacestar.back.chat.vo.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomResVo {
    private int index;
    private String roomNumber;
    private String otherMemberUuid;
}
