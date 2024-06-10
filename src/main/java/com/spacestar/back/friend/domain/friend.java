package com.spacestar.back.friend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class friend {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isFriend;

    @Column(length = 100)
    private String senderUuid;

    @Column(length = 100)
    private String receiverUuid;
}
