package com.spacestar.back.chat.domain.entity;


import com.spacestar.back.global.GlobalCreateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ChatRoom extends GlobalCreateTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String roomNumber;

    @Builder
    public ChatRoom(String roomNumber) {
        this.roomNumber = roomNumber;
    }

}
