package com.spacestar.back.auth.domain;

import com.spacestar.back.auth.enums.GenderType;
import com.spacestar.back.auth.enums.UnregisterType;
import com.spacestar.back.global.GlobalTime;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends GlobalTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 100)
    private String uuid;

    @NotNull
    @Column(length = 50)
    private String email;

    @Column(length = 50)
    private String nickname;

    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    private GenderType gender;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UnregisterType unregister;

    @NotNull
    private boolean infoAgree;

    @Builder
    public Member(Long id, String uuid, String email, String nickname, LocalDate birth, GenderType gender, UnregisterType unregister, boolean infoAgree) {
        this.id = id;
        this.uuid = uuid;
        this.email = email;
        this.nickname = nickname;
        this.birth = birth;
        this.gender = gender;
        this.unregister = unregister;
        this.infoAgree = infoAgree;
    }

}
