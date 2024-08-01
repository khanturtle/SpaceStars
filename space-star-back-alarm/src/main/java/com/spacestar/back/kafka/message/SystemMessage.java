package com.spacestar.back.kafka.message;

import com.spacestar.back.alarm.enums.AlarmType;

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
public class SystemMessage implements Message{

	private String senderUuid;
	private String receiverUuid;
	private String content;

	@Override
	public AlarmType getMessageType(){
		return AlarmType.SYSTEM;
	}

	public static SystemMessage createConnectionSuccessMessage(String receiverUuid){
		return SystemMessage.builder()
				.senderUuid("System")
				.receiverUuid(receiverUuid)
				.content("연결에 성공하였습니다.")
				.build();
	}
}
