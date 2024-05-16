package com.spacestar.back.game.vo;

import com.spacestar.back.game.domain.Game;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GameResVo {
    private String gameName;
    private String gameImage;

    public GameResVo(String gameName,String gameImage){
        this.gameName=gameName;
        this.gameImage=gameImage;
    }
}
