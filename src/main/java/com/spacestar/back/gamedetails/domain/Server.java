package com.spacestar.back.gamedetails.domain;

import com.spacestar.back.game.domain.Game;
import com.spacestar.back.gamegenre.domain.GameGenre;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Server {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Game game;

    private String serverName;
    private String serverNameKor;
    private String serverImage;
}
