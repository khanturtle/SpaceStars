package com.spacestar.back.chat.domain.entity;


import com.spacestar.back.global.GlobalCreateTime;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class ChatRoom extends GlobalCreateTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String roomNumber;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ChatMember> chatMembers;


    @Builder
    public ChatRoom(String roomNumber) {
        this.roomNumber = roomNumber;
    }

}
