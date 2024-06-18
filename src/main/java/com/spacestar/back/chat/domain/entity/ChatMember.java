package com.spacestar.back.chat.domain.entity;


import com.spacestar.back.chat.enums.teamParticipationType;
import com.spacestar.back.global.GlobalTime;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ChatMember extends GlobalTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ChatRoom chatRoom;

    private String memberUuid;

    @Enumerated(EnumType.STRING)
    private teamParticipationType participationType;

    @Builder
    public ChatMember(ChatRoom chatRoom, String memberUuid, teamParticipationType participationType) {
        this.chatRoom = chatRoom;
        this.memberUuid = memberUuid;
        this.participationType = participationType;
    }


}
