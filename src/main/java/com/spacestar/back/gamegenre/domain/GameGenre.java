package com.spacestar.back.gamegenre.domain;

import com.spacestar.back.game.domain.Game;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class GameGenre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String genre;
    @OneToMany(mappedBy = "gameGenre",orphanRemoval = true)
    private List<Game> gameList = new ArrayList<Game>();

    @Builder
    public GameGenre(String genre){
        this.genre = genre;
    }
}
