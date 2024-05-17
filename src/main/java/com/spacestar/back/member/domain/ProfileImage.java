package com.spacestar.back.member.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileImage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Column
    private String profileImageUrl;

    @Column
    private Boolean main;

    @Column
    private Integer idx;

//    @Builder
//    public ProfileImage(Long id, Member member, String profileImageUrl, Boolean isMain, Integer index) {
//        this.id = id;
//        this.member = member;
//        this.profileImageUrl = profileImageUrl;
//        this.isMain = isMain;
//        this.index = index;
//    }
}
