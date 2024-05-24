package com.spacestar.back.member.domain;

import com.spacestar.back.member.dto.req.ProfileImageReqDto;
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

    private String profileImageUrl;

    private boolean main;

    private int idx;


    public static ProfileImage addNewImage(Member member, ProfileImageReqDto profileImageReqDto) {

            return ProfileImage.builder()
                    .member(member)
                    .profileImageUrl(profileImageReqDto.getProfileImageUrl())
                    .main(profileImageReqDto.isMainImage())
                    .idx(profileImageReqDto.getIdx())
                    .build();
    }

    public static ProfileImage updateImage(ProfileImage profileImage, ProfileImageReqDto profileImageReqDto) {

        return ProfileImage.builder()
                .id(profileImage.getId())
                .member(profileImage.getMember())
                .profileImageUrl(profileImageReqDto.getProfileImageUrl())
                .main(profileImageReqDto.isMainImage())
                .idx(profileImageReqDto.getIdx())
                .build();
    }

}
