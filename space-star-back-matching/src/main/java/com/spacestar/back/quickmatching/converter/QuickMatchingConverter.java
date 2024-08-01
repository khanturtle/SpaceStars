package com.spacestar.back.quickmatching.converter;

import com.spacestar.back.quickmatching.domain.QuickMatchStatus;
import com.spacestar.back.quickmatching.domain.QuickMatching;

public class QuickMatchingConverter {

    public static QuickMatching toEntity(String matchFromMember,String matchToMember) {
        return QuickMatching.builder()
                .id(matchFromMember + matchToMember)
                .matchFromMember(matchFromMember)
                .matchToMember(matchToMember)
                .matchToMemberStatus(QuickMatchStatus.WAIT)
                .matchFromMemberStatus(QuickMatchStatus.WAIT)
                .build();
    }
}
