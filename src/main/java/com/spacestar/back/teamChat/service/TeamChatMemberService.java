package com.spacestar.back.teamChat.service;

import com.spacestar.back.teamChat.domain.entity.TeamChatRoom;

public interface TeamChatMemberService {
    void addMemberToTeamChatRoom(TeamChatRoom teamChatRoom, String uuid,Boolean ownerStatus);
    void deleteMemberToTeamChatRoom(TeamChatRoom teamChatRoom, String uuid);

}
