package com.spacestar.back.kafka.message;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MatchingMessage {

	private String senderUuid;
	private String receiverUuid;
	private String content;
}
