package com.spacestar.back.swipe.domain;

import com.spacestar.back.global.GlobalTime;
import com.spacestar.back.swipe.SwipeStatus;
import com.spacestar.back.swipe.dto.req.SwipeReqDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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