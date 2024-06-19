package com.spacestar.back.friend.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FriendSendResDto {

    private int index;
    private String friendSendUuid;

    public static FriendSendResDto toDto(int i, String friendUuid) {
        return FriendSendResDto.builder()
                .index(i)
                .friendSendUuid(friendUuid)
                .build();
    }
}
