package com.spacestar.back.teamChat.domain.entity;

import com.spacestar.back.global.GlobalTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class TeamChatRoom extends GlobalTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long gameId;
    private String roomNumber;

    private String roomName;

    private String memo;

    private Integer maxMembers;

    private Boolean isFinished;

    private Boolean isPassword;

    private String password;


    @Builder
    public TeamChatRoom(Long id,String roomNumber, String roomName, Boolean isPassword, String password, Integer maxMembers, Boolean isFinished, Long gameId, String memo) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.roomName = roomName;
        this.isPassword = isPassword;
        this.password = password;
        this.maxMembers = maxMembers;
        this.isFinished = isFinished;
        this.gameId = gameId;
        this.memo = memo;
    }



}
