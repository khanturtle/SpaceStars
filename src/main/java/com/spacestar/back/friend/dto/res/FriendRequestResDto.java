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
public class FriendRequestResDto {

    private int index;
    private String friendRequestUuid;

    public static FriendRequestResDto toDto(int i, String friendRequestUuid) {
        return FriendRequestResDto.builder()
                .index(i)
                .friendRequestUuid(friendRequestUuid)
                .build();
    }


}
