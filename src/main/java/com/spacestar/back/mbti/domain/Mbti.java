package com.spacestar.back.mbti.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Mbti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10)
    private String mbtiName;

    @Builder
    public Mbti(Long id, String mbtiName) {
        this.id = id;
        this.mbtiName = mbtiName;
    }
}
