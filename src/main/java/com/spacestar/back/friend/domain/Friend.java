package com.spacestar.back.friend.domain;

import com.spacestar.back.friend.enums.FriendType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Friend {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private FriendType friendType;

    @Column(length = 100)
    private String uuid;

    @Column(length = 100)
    private String friendUuid;

    @Builder
    public Friend(Long id, FriendType friendType, String uuid, String friendUuid) {
        this.id = id;
        this.friendType = friendType;
        this.uuid = uuid;
        this.friendUuid = friendUuid;
    }
}
