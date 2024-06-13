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
    @Column(length = 50)
    private String gameServerName;
    @Column(length = 50)
    private String gameServerNameKor;
    @Column(columnDefinition = "TEXT")
    private String gameServerImage;

    @Builder
    public GameServer(Game game, String gameServerName, String gameServerNameKor, String gameServerImage) {
        this.game = game;
        this.gameServerName = gameServerName;
        this.gameServerNameKor = gameServerNameKor;
        this.gameServerImage = gameServerImage;
    }
}
