package com.spacestar.back.gamedetails.domain;

import com.spacestar.back.game.domain.Game;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class GameTier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Game game;
    private String gameTierName;
    private String gameTierNameKor;
    private String gameTierImage;

    @Builder
    public GameTier(Game game, String gameTierName, String gameTierNameKor, String gameTierImage) {
        this.game = game;
        this.gameTierName = gameTierName;
        this.gameTierNameKor = gameTierNameKor;
        this.gameTierImage = gameTierImage;
    }
}
