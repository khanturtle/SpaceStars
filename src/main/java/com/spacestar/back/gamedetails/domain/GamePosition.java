package com.spacestar.back.gamedetails.domain;

import com.spacestar.back.game.domain.Game;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class GamePosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Game game;
    @Column(length = 20)
    private String gamePositionName;
    @Column(length = 20)
    private String gamePositionNameKor;
    @Column(length = 255)
    private String gamePositionImage;

    @Builder
    public GamePosition(Game game, String gamePositionName, String gamePositionNameKor, String gamePositionImage) {
        this.game = game;
        this.gamePositionName = gamePositionName;
        this.gamePositionNameKor = gamePositionNameKor;
        this.gamePositionImage = gamePositionImage;
    }
}
