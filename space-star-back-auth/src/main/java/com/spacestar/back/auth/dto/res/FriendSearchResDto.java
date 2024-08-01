package com.spacestar.back.auth.dto.res;

import com.spacestar.back.auth.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FriendSearchResDto {

    private int index;
    private String uuid;
    private String nickname;

    public static FriendSearchResDto toDto(int index, Member member){
        return FriendSearchResDto.builder()
                .index(index)
                .uuid(member.getUuid())
                .nickname(member.getNickname())
                .build();
    }
}
