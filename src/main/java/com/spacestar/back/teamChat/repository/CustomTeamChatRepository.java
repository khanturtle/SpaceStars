package com.spacestar.back.teamChat.repository;

import com.spacestar.back.teamChat.domain.entity.TeamChatRoom;

import java.util.List;

public interface CustomTeamChatRepository {
    List<TeamChatRoom> findQueryDslTeamChatRoom(Long gameId, Integer maxMembers, Boolean isFinished);
}
