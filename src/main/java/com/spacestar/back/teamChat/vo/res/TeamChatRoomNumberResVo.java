package com.spacestar.back.teamChat.vo.res;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamChatRoomNumberResVo {

        private String roomNumber;

        public TeamChatRoomNumberResVo(String roomNumber) {
            this.roomNumber = roomNumber;
        }
}
