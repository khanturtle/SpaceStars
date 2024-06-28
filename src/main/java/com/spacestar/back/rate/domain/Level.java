package com.spacestar.back.rate.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(columnDefinition = "TEXT")
    private String levelIcon;
    @NotNull
    @Column(length = 5)
    private int level;
    @NotNull
    @Column(length = 10)
    private int startExp;
    @NotNull
    @Column(length = 10)
    private int endExp;

    @Builder
    public Level(Long id, String levelIcon, int level, int startExp, int endExp) {
        this.id = id;
        this.levelIcon = levelIcon;
        this.level = level;
        this.startExp = startExp;
        this.endExp = endExp;
    }
}
