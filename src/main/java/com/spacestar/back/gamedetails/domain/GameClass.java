package com.spacestar.back.gamedetails.domain;

import com.spacestar.back.game.domain.Game;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class GameClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Game game;
    @Column(length = 50)
    private String gameClassName;
    @Column(length = 50)
    private String gameClassNameKor;
    @Column(columnDefinition = "TEXT")
    private String gameClassImage;

    @Builder
    public GameClass(Game game, String gameClassName, String gameClassNameKor, String gameClassImage) {
        this.game = game;
        this.gameClassName = gameClassName;
        this.gameClassNameKor = gameClassNameKor;
        this.gameClassImage = gameClassImage;
    }
}
