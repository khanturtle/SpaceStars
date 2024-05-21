package com.spacestar.back.swipe.domain;

import com.spacestar.back.global.GlobalTime;
import com.spacestar.back.swipe.SwipeStatus;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Swipe extends GlobalTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String matchToMember;
    private String matchFromMember;
    @Enumerated(EnumType.STRING)
    private SwipeStatus status;
    private String memo;
}