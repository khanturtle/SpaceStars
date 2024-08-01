package com.spacestar.back.block.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Block {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String uuid;

    @Column(length = 100)
    private String targetUuid;

    @Builder
    public Block(Long id, String uuid, String targetUuid) {
        this.id = id;
        this.uuid = uuid;
        this.targetUuid = targetUuid;
    }
}
