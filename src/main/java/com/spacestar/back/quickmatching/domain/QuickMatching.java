package com.spacestar.back.quickmatching.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class QuickMatching {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String matchToMember;
    private String matchFromMember;

    public QuickMatching(String fSessionId, String mSessionId) {
        this.matchFromMember = fSessionId;
        this.matchToMember = mSessionId;
    }

}
