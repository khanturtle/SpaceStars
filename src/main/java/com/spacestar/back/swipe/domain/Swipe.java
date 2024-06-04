package com.spacestar.back.swipe.domain;

import com.spacestar.back.global.GlobalTime;
import com.spacestar.back.swipe.SwipeStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Swipe extends GlobalTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String matchToMember;
    @Column(nullable = false)
    private String matchFromMember;
    @Column(length = 30)
    private String memo;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SwipeStatus status;

    @Builder
    public Swipe(Long id, String matchToMember, String matchFromMember, String memo, SwipeStatus status) {
        this.id = id;
        this.matchToMember = matchToMember;
        this.matchFromMember = matchFromMember;
        this.memo = memo;
        this.status = status;
    }
}