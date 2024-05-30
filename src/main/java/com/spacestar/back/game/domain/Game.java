package com.spacestar.back.game.domain;

import com.spacestar.back.gamegenre.domain.GameGenre;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private GameGenre gameGenre;
    @NotNull
    private String name;
    @NotNull
    private String nameKor;
    @NotNull
    private String image;
    @NotNull
    private boolean isTier;
    @NotNull
    private boolean isPosition;
    @NotNull
    private boolean isClass;
    @NotNull
    private boolean isServer;


    @Builder
    public Game(GameGenre gameGenre, String name, String nameKor, String image, boolean isTier, boolean isPosition, boolean isClass, boolean isServer) {
        this.gameGenre = gameGenre;
        this.name = name;
        this.nameKor = nameKor;
        this.image = image;
        this.isTier = isTier;
        this.isPosition = isPosition;
        this.isClass = isClass;
        this.isServer = isServer;
    }

}
