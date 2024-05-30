package com.spacestar.back.game.domain;

import com.spacestar.back.gamedetails.domain.GameClass;
import com.spacestar.back.gamedetails.domain.GamePosition;
import com.spacestar.back.gamedetails.domain.GameServer;
import com.spacestar.back.gamedetails.domain.GameTier;
import com.spacestar.back.gamegenre.domain.GameGenre;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "gaeme", orphanRemoval = true)
    private List<GameTier> gameTiers = new ArrayList<GameTier>();
    @OneToMany(mappedBy = "gaeme", orphanRemoval = true)
    private List<GamePosition> isPositions = new ArrayList<GamePosition>();
    @OneToMany(mappedBy = "gaeme", orphanRemoval = true)
    private List<GameClass> isClasses = new ArrayList<GameClass>();
    @OneToMany(mappedBy = "gaeme", orphanRemoval = true)
    private List<GameServer> gameServers = new ArrayList<GameServer>();

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
