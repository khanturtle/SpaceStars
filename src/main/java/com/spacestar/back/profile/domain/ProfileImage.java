package com.spacestar.back.profile.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Profile profile;

    @NotNull
    @Column(length = 255)
    private String profileImageUrl;

    @NotNull
    private boolean main;

    private int idx;

    @Builder
    public ProfileImage(Long id, Profile profile, String profileImageUrl, boolean main, int idx) {
        this.id = id;
        this.profile = profile;
        this.profileImageUrl = profileImageUrl;
        this.main = main;
        this.idx = idx;
    }
}
