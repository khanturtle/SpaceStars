package com.spacestar.back.quickmatching.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor
public class QuickMatching implements Serializable {
    @Id
    private String id;
    private String matchToMember;
    private String matchFromMember;
    private QuickMatchStatus matchToMemberStatus;
    private QuickMatchStatus matchFromMemberStatus;

    @Builder
    public QuickMatching(String id, String matchFromMember, String matchToMember, QuickMatchStatus matchToMemberStatus, QuickMatchStatus matchFromMemberStatus) {
        this.id = id;
        this.matchToMemberStatus = matchToMemberStatus;
        this.matchFromMemberStatus = matchFromMemberStatus;
        this.matchFromMember = matchFromMember;
        this.matchToMember = matchToMember;
    }
}