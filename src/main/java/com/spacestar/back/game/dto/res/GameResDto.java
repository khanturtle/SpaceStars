package com.spacestar.back.game.dto.res;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameResDto {
    private int index;
    private String gameName;
    private String gameImage;
}
