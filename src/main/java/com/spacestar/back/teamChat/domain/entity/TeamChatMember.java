package com.spacestar.back.teamChat.domain.entity;

import com.spacestar.back.global.GlobalTime;
import com.spacestar.back.teamChat.enums.TeamParticipationType;
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
    private TeamParticipationType teamParticipationType;
    @Builder
    public TeamChatMember(Long id,TeamChatRoom teamChatRoom, String memberUuid, Boolean ownerStatus, TeamParticipationType teamParticipationType) {
        this.id = id;
        this.teamChatRoom = teamChatRoom;
        this.memberUuid = memberUuid;
        this.ownerStatus = ownerStatus;
        this.teamParticipationType = teamParticipationType;
    }

}
