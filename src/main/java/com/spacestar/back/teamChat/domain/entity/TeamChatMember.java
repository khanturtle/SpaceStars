package com.spacestar.back.teamChat.domain.entity;

import com.spacestar.back.chat.enums.ParticpationType;
import com.spacestar.back.global.GlobalTime;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class TeamChatMember extends GlobalTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private TeamChatRoom teamChatRoom;

    private String memberUuid;

    private Boolean ownerStatus;

    @Enumerated(EnumType.STRING)
    private ParticpationType particpationType;

    @Builder
    public TeamChatMember(TeamChatRoom teamChatRoom, String memberUuid, Boolean ownerStatus,ParticpationType particpationType ) {
        this.teamChatRoom = teamChatRoom;
        this.memberUuid = memberUuid;
        this.ownerStatus = ownerStatus;
        this.particpationType = particpationType;
    }

}
