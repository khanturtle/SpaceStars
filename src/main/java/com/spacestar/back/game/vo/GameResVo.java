package com.spacestar.back.game.vo;

import com.querydsl.core.annotations.QueryProjection;
import com.spacestar.back.game.domain.Game;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameResVo {
    private int index;
    private String gameName;
    private String gameImage;
}
