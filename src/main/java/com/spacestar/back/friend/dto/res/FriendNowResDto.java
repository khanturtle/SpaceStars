package com.spacestar.back.friend.dto.res;

import com.spacestar.back.friend.enums.FriendNowType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FriendNowResDto {

    private FriendNowType friendType;
}
