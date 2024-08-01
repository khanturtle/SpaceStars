package com.spacestar.back.kafka.message;

import com.spacestar.back.swipe.dto.req.SwipeReqDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MatchingMessage {

	private String senderUuid;
	private String receiverUuid;
	private String content;

	public static MatchingMessage toMatchingMessage(String senderUuid, SwipeReqDto swipeReqDto) {
		return MatchingMessage.builder()
			.senderUuid(senderUuid)
			.receiverUuid(swipeReqDto.getMatchToMember())
			.content(swipeReqDto.getMemo())
			.build();
	}
}
