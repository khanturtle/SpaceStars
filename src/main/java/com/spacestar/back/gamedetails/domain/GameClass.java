package com.spacestar.back.gamedetails.domain;

import com.spacestar.back.game.domain.Game;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class GameClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Game game;
    private String gameClassName;
    private String gameClassNameKor;
    private String gameClassImage;
}
