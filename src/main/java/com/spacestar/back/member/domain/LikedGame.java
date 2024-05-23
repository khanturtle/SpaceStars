package com.spacestar.back.member.domain;

import com.spacestar.back.member.dto.req.MemberInfoReqDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class LikedGame {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long gameId;

    private String uuid;

    @Builder
    public LikedGame(Long id, Long gameId, String uuid) {
        this.id = id;
        this.gameId = gameId;
        this.uuid = uuid;
    }

}
