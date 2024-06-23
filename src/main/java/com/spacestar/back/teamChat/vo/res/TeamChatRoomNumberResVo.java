package com.spacestar.back.teamChat.vo.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TeamChatRoomNumberResVo {

        private String roomNumber;

        public TeamChatRoomNumberResVo(String roomNumber) {
            this.roomNumber = roomNumber;
        }
}
