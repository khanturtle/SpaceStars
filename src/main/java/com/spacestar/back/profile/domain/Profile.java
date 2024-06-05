package com.spacestar.back.profile.domain;

import com.spacestar.back.global.GlobalTime;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jdk.jshell.Snippet;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Profile extends GlobalTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 100)
    private String uuid;

    @Column(length = 50)
    private String introduce;

    private Long gamePreferenceId;

    private Long mbtiId;

    private Long exp;

    private int reportCount;

    private boolean swipe;

    @Builder
    public Profile(Long id, String uuid, String introduce, Long gamePreferenceId, Long mbtiId, Long exp, int reportCount, boolean swipe) {
        this.id = id;
        this.uuid = uuid;
        this.introduce = introduce;
        this.gamePreferenceId = gamePreferenceId;
        this.mbtiId = mbtiId;
        this.exp = exp;
        this.reportCount = reportCount;
        this.swipe = swipe;
    }

}
