package com.spacestar.back.kafka;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendMessage {

    private String senderUuid;
    private String receiverUuid;
    private String content;
}
