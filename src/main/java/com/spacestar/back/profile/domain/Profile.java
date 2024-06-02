package com.spacestar.back.profile.domain;

import com.spacestar.back.global.GlobalTime;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @Column(length = 20)
    private String nickname;

    @Column(length = 50)
    private String introduce;

    private Long gamePreferenceId;

    private Long mbtiId;

    @NotNull
    private Long exp;

    private int reportCount;

    private boolean swipe;

}
