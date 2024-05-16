package com.spacestar.back.game.vo;

import com.spacestar.back.game.domain.Game;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GameResVo {
    private int index;
    private String gameName;
    private String gameImage;

    public GameResVo(int index, String gameName,String gameImage){
        this.index = index;
        this.gameName=gameName;
        this.gameImage=gameImage;
    }
}
