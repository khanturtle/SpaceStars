package com.spacestar.back.kafka.message;

public interface Message {

	String getSenderUuid();
	String getReceiverUuid();
	String getContent();

}
