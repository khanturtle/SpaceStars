package com.spacestar.back.gamedetails.domain;

import com.spacestar.back.game.domain.Game;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class GameServer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Game game;
    private String gameServerName;
    private String gameServerNameKor;
    private String gameServerImage;

    @Builder
    public GameServer(Game game, String gameServerName, String gameServerNameKor, String gameServerImage) {
        this.game = game;
        this.gameServerName = gameServerName;
        this.gameServerNameKor = gameServerNameKor;
        this.gameServerImage = gameServerImage;
    }
}
