package com.spacestar.back.game.vo.res;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameResVo {
    private int index;
    private String gameName;
    private String gameImage;
}
