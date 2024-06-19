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
public class FriendListResDto {

    private int index;
    private String friendUuid;

    public static FriendListResDto toDto(int i,String friendUuid) {
        return FriendListResDto.builder()
                .index(i)
                .friendUuid(friendUuid)
                .build();
    }

}
