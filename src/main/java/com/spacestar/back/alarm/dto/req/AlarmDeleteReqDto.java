package com.spacestar.back.alarm.dto.req;

import java.util.List;

import lombok.Getter;

@Getter
public class AlarmDeleteReqDto {

	List<String> alarmIds;
}
