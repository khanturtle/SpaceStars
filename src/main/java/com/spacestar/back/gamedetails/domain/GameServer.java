package com.spacestar.back.gamedetails.domain;

import com.spacestar.back.game.domain.Game;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class GameServer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Game game;
    private String gameServerName;
    private String gameServerNameKor;
    private String gameServerImage;
}
