package com.spacestar.back.kafka.message;

import com.spacestar.back.alarm.enums.AlarmType;

public interface Message {

	String getSenderUuid();
	String getReceiverUuid();
	String getContent();

	AlarmType getMessageType();
}
